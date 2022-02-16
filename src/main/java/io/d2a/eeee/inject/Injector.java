package io.d2a.eeee.inject;

import java.lang.reflect.Field;

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

}
