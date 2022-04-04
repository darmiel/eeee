package io.d2a.eeee.annotation.annotations.other;

import io.d2a.eeee.util.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;

public class AccessGetSet {

    @SuppressWarnings("unchecked")
    public static <T> T create(final Object instance, final Class<T> clazz) {
        return (T) Proxy.newProxyInstance(
            instance.getClass().getClassLoader(),
            new Class[]{clazz},
            (proxy, method, args) -> {
                if (!method.isAnnotationPresent(Target.class)) {
                    return method.invoke(proxy, args);
                }

                final String target = method.getAnnotation(Target.class).value();

                final Field field = instance.getClass().getDeclaredField(target);
                field.setAccessible(true);

                if (method.getName().startsWith("get")) {
                    return field.get(instance);
                } else if (method.getName().startsWith("set")) {
                    if (Modifier.isFinal(field.getModifiers())) {
                        throw new IllegalArgumentException("field is final.");
                    }
                    if (args.length != 1) {
                        throw new IllegalArgumentException("argument count should be 1.");
                    }
                    field.set(instance, args[0]);
                }
                return null;
            }
        );
    }

}
