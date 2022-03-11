package io.d2a.eeee.inject;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Injector {

    public static final Injector EMPTY = new Injector();

    private static final Logger logger = Logger.getLogger("Injector");
    private final BiMap<Class<?>, String, Object> dependencies = new BiMap<>();

    public Injector() {
        this.register(Injector.class, this); // default register injector
    }

    public <T> Injector register(final Class<T> clazz, final T t, final String name) {
        this.dependencies.put(clazz, name, t);
        return this;
    }

    public <T> Injector register(final Class<T> clazz, final T t) {
        this.dependencies.put(clazz, null, t);
        return this;
    }

    @SuppressWarnings("unchecked")
    public <T> T find(final Class<T> clazz, final String name) {
        if (this.dependencies.containsKey(clazz, name)) {
            return (T) this.dependencies.get(clazz, name);
        }
        return null;
    }

    public <T> T find(final Class<T> clazz) {
        return this.find(clazz, "");
    }

    public void injectFields(final Object instance) throws IllegalAccessException {
        for (final Field field : instance.getClass().getDeclaredFields()) {
            // ignore final fields
            if (Modifier.isFinal(field.getModifiers())) {
                continue;
            }
            // only inject into injectable fields
            if (!field.isAnnotationPresent(Inject.class)) {
                continue;
            }
            field.setAccessible(true);

            // find instance of injected field
            final Inject inject = field.getAnnotation(Inject.class);
            final Object value = this.find(field.getType(), inject.value());
            if (value == null) {
                throw new NullPointerException("injected value was null");
            }

            logger.info("Injected field " + field.getName());
            field.set(instance, value);
        }
    }

    public <T> T create(final Constructor<T> constructor, final InjectParameterSupplier supplier)
        throws Exception {
        final List<Object> parameters = new ArrayList<>();
        for (final Parameter parameter : constructor.getParameters()) {
            final Inject inject = parameter.getAnnotation(Inject.class);

            final Object obj;
            if (inject == null) {
                obj = supplier != null ? supplier.supply(parameter) : null;
            } else {
                obj = this.find(parameter.getType(), inject.value());
            }
            parameters.add(obj);
        }

        final T instance = constructor.newInstance(parameters.toArray());

        // inject field values
        this.injectFields(instance);

        return instance;
    }

    public <T> T create(final Class<T> clazz) throws Exception {
        return create(clazz, null);
    }
    
    public <T> T create(final Class<T> clazz, final InjectParameterSupplier supplier)
        throws Exception {

        Constructor<T> constructor;
        try {
            constructor = this.findInjectableConstructor(clazz);
        } catch (NoSuchMethodException nsmex) {
            constructor = clazz.getDeclaredConstructor();
        }
        constructor.setAccessible(true);

        return this.create(constructor, supplier);
    }

    private <T> Constructor<T> findInjectableConstructor(final Class<T> clazz)
        throws NoSuchMethodException {
        for (final Constructor<?> constr : clazz.getDeclaredConstructors()) {
            for (final Parameter parameter : constr.getParameters()) {
                if (parameter.isAnnotationPresent(Inject.class)) {
                    return clazz.getDeclaredConstructor(constr.getParameterTypes());
                }
            }
        }
        throw new NoSuchMethodException("injectable constructor not found");
    }

}
