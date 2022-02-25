package io.d2a.eeee.generate;

import io.d2a.eeee.EntryMethod;
import io.d2a.eeee.annotation.annotations.Prompt;
import io.d2a.eeee.annotation.annotations.Use;
import io.d2a.eeee.annotation.provider.AnnotationProvider;
import io.d2a.eeee.inject.Inject;
import io.d2a.eeee.inject.Injector;
import io.d2a.eeee.nw.Wrappers;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Factory {

    public static <T> T createClass(final Scanner scanner, final Class<T> clazz) throws Exception {
        return createClass(scanner, clazz, null);
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
        final List<Object> parameters = new ArrayList<>();

        for (final Parameter parameter : constructor.getParameters()) {
            parameters.add(createSingleObject(
                scanner,
                parameter::getAnnotation,
                parameter.getName(),
                parameter.getType(),
                injector
            ));
        }

        return constructor.newInstance(parameters.toArray());
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

        // inject fields
        if (injector != null) {
            injector.injectFields(t);
        }

        // get all prompt values and request
        for (final Field field : clazz.getDeclaredFields()) {
            if (!field.isAnnotationPresent(Prompt.class)) {
                continue;
            }
            field.setAccessible(true);

            final Object val = createSingleObject(
                scanner,
                field::getAnnotation,
                field.getName(),
                field.getType(),
                injector
            );

            field.set(t, val);
        }

        return t;
    }

}
