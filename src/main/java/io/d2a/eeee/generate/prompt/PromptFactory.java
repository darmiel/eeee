package io.d2a.eeee.generate.prompt;

import io.d2a.eeee.annotation.annotations.common.Use;
import io.d2a.eeee.annotation.annotations.prompt.Prompt;
import io.d2a.eeee.annotation.provider.AnnotationProvider;
import io.d2a.eeee.inject.Inject;
import io.d2a.eeee.inject.Injector;
import io.d2a.eeee.prompt.Wrappers;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Scanner;

public class PromptFactory {

    public static <T> T createClass(final Scanner scanner, final Class<T> clazz) throws Exception {
        return createClass(scanner, clazz, Injector.EMPTY);
    }

    private static <T> Constructor<T> findConstructor(final Class<T> clazz)
        throws NoSuchMethodException {

        for (final Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            if (constructor.isAnnotationPresent(Prompt.class)) {
                return clazz.getDeclaredConstructor(constructor.getParameterTypes());
            }
        }

        return clazz.getDeclaredConstructor();
    }

    private static Object createSingleObject(
        final Scanner scanner,
        final AnnotationProvider provider,
        final String name,
        final Class<?> objType,
        final Injector injector
    ) throws Exception {
        // can this value be injected?
        final Inject inject = provider.get(Inject.class);
        if (inject != null) {
            return injector.find(objType, inject.value());
        }

        final String displayPrompt;
        final Prompt prompt = provider.get(Prompt.class);
        if (prompt != null) {
            displayPrompt = prompt.value();
        } else {
            displayPrompt = name;
        }

        final Class<?> type;
        final Use use = provider.get(Use.class);
        if (use != null) {
            type = use.value();
        } else {
            type = objType;
        }

        // request value
        return Wrappers.requestValue(
            scanner,
            type,
            displayPrompt,
            provider
        );
    }

    private static <T> T createClassByConstructor(
        final Scanner scanner,
        final Constructor<T> constructor,
        final Injector injector
    ) throws Exception {
        return injector.create(constructor, parameter -> createSingleObject(
            scanner,
            parameter::getAnnotation,
            parameter.getName(),
            parameter.getType(),
            injector
        ));
    }

    public static <T> T createClass(
        final Scanner scanner,
        final Class<T> clazz,
        final Injector injector
    ) throws Exception {
        // create class
        final Constructor<T> constructor = findConstructor(clazz);
        constructor.setAccessible(true);

        final T t = createClassByConstructor(scanner, constructor, injector);

        // get all prompt values and request
        // this should be deprecated in the future
        // bc why on earth you don't want to use the constructor?
        for (final Field field : clazz.getDeclaredFields()) {
            // ignore final fields
            if (Modifier.isFinal(field.getModifiers())) {
                continue;
            }
            if (!field.isAnnotationPresent(Prompt.class)) {
                continue;
            }
            field.setAccessible(true);

            field.set(t, createSingleObject(
                scanner,
                field::getAnnotation,
                field.getName(),
                field.getType(),
                injector
            ));
        }

        return t;
    }

}
