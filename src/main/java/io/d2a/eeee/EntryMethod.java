package io.d2a.eeee;

import io.d2a.eeee.annotation.provider.AnnotationProvider;
import io.d2a.eeee.annotation.annotations.Entrypoint;
import io.d2a.eeee.annotation.annotations.Prompt;
import io.d2a.eeee.inject.Injector;
import io.d2a.eeee.wrapper.Wrapper;
import io.d2a.eeee.wrapper.Wrappers;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EntryMethod {

    public final Method method;
    public final Entrypoint entrypoint;
    public final Class<?> clazz;

    public EntryMethod(final Method method, final Entrypoint entrypoint, final Class<?> clazz) {
        this.method = method;
        this.entrypoint = entrypoint;
        this.clazz = clazz;
    }

    public static Object promptValue(
        final Scanner scanner,
        final Class<?> type,
        final String prompt,
        final AnnotationProvider provider
    ) throws Exception {

        Wrapper<?> wrapper = Wrappers.WRAPPERS.get(type);
        if (wrapper == null) {
            if (!Wrapper.class.isAssignableFrom(type)) {
                throw new IllegalArgumentException("cannot find wrapper for parameter " +
                    type.getSimpleName());
            }
            final Constructor<?> wrapperConstructor = type.getDeclaredConstructor();
            wrapperConstructor.setAccessible(true);
            wrapper = (Wrapper<?>) wrapperConstructor.newInstance();
            Wrappers.WRAPPERS.put(type, wrapper);
        }

        // execute wrapper
        return wrapper.wrap(scanner, prompt, provider);
    }

    public void invoke(final Scanner scanner, final Injector injector) throws Exception {
        final String typeStr = Starter.formatTypes(method.getParameterTypes(), true);

        // create starter class using injector
        final Object instance = injector.create(this.clazz);

        final List<Object> parameters = new ArrayList<>();

        // get parameters from method
        if (this.entrypoint.verbose()) {
            System.out.printf("[Runner] Requesting parameters (%s) ...%n", typeStr);
        }

        // request parameters
        if (this.method.getParameterCount() > 0) {
            if (this.entrypoint.verbose()) {
                System.out.println();
            }

            for (final Parameter parameter : this.method.getParameters()) {
                final String prompt;
                if (parameter.isAnnotationPresent(Prompt.class)) {
                    prompt = parameter.getAnnotation(Prompt.class).value();
                } else {
                    prompt = parameter.getName();
                }

                // execute wrapper
                parameters.add(promptValue(
                    scanner,
                    parameter.getType(),
                    prompt,
                    parameter::getAnnotation
                ));
            }
        }
        System.out.println();

        if (this.entrypoint.verbose()) {
            // invoke method
            System.out.printf("[Runner] Invoking %s@%s w/ (%s)...%n",
                method.getName(), clazz.getSimpleName(), typeStr);
        }

        System.out.println("---");

        final long timeStart = System.currentTimeMillis();
        this.method.invoke(instance, parameters.toArray());
        final long timeEnd = System.currentTimeMillis();

        System.out.println("---");

        if (this.entrypoint.stopwatch()) {
            System.out.printf("Execution complete! Took approx. %dms.%n", timeEnd - timeStart);
        }
    }

}
