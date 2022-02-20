package io.d2a.eeee.inject;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

public class Injector {

    private BiMap<Class<?>, String, Object> dependencies = new BiMap<>();

    public <T> Injector register(final Class<T> clazz, final T t, final String name) {
        this.dependencies.put(clazz, name, t);
        return this;
    }

    public <T> Injector register(final Class<T> clazz, final T t) {
        this.dependencies.put(clazz, null, t);
        return this;
    }

    public Object find(final Class<?> clazz, final String name) {
        if (this.dependencies.containsKey(clazz, name)) {
            return this.dependencies.get(clazz, name);
        }
        return null;
    }

    public void inject(final Object instance) throws IllegalAccessException {
        for (final Field field : instance.getClass().getDeclaredFields()) {
            if (!field.isAnnotationPresent(Inject.class)) {
                continue;
            }
            field.setAccessible(true);

            final Inject inject = field.getAnnotation(Inject.class);

            // find instance of injected field
            final Object value = this.find(field.getType(), inject.value());
            if (value == null) {
                System.out.println("ERROR: Injected value is null");
                return;
            }

            System.out.printf("[Injector] injected field %s%n", field.getName());
            field.set(instance, value);
        }
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

    public <T> T create(final Class<T> clazz) throws
        NoSuchMethodException,
        InvocationTargetException,
        InstantiationException,
        IllegalAccessException {

        Constructor<T> constructor; // = this.findInjectableConstructor(clazz);

        try {
            constructor = this.findInjectableConstructor(clazz);
        } catch (NoSuchMethodException nsmex) {
            constructor = clazz.getDeclaredConstructor();
        }

        constructor.setAccessible(true);

        final List<Object> parameters = new ArrayList<>();
        for (final Parameter parameter : constructor.getParameters()) {
            final Inject inject = parameter.getAnnotation(Inject.class);
            if (inject == null) {
                parameters.add(null);
                continue;
            }
            parameters.add(this.find(parameter.getType(), inject.value()));
        }

        final T instance = constructor.newInstance(parameters.toArray());

        // inject field values
        this.inject(instance);

        return instance;
    }

}
