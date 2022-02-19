package io.d2a.eeee.generate;

import io.d2a.eeee.EntryMethod;
import io.d2a.eeee.annotation.annotations.Prompt;
import io.d2a.eeee.inject.Injector;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Scanner;

public class Factory {

    public static <T> T createClass(final Scanner scanner, final Class<T> clazz) throws Exception {
        return createClass(scanner, clazz, null);
    }

    public static <T> T createClass(final Scanner scanner, final Class<T> clazz, final Injector injector) throws Exception {
        // create class
        final Constructor<T> constructor = clazz.getDeclaredConstructor();
        final T t = constructor.newInstance();

        // inject fields
        if (injector != null) {
            injector.inject(t);
        }

        // get all prompt values and request
        for (final Field field : clazz.getDeclaredFields()) {
            if (!field.isAnnotationPresent(Prompt.class)) {
                continue;
            }
            field.setAccessible(true);
            final String prompt;
            if (field.isAnnotationPresent(Prompt.class)) {
                prompt = field.getAnnotation(Prompt.class).value();
            } else {
                prompt = field.getName();
            }

            // request value
            final Object object = EntryMethod.promptValue(
                scanner,
                field.getType(),
                prompt,
                field::getAnnotation
            );

            field.set(t, object);
        }

        return t;
    }

}
