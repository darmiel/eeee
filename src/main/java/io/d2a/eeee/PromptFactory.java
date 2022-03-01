package io.d2a.eeee;

import io.d2a.eeee.annotation.annotations.common.Use;
import io.d2a.eeee.annotation.annotations.generate.Generate;
import io.d2a.eeee.annotation.annotations.prompt.Factory;
import io.d2a.eeee.annotation.annotations.prompt.Prompt;
import io.d2a.eeee.annotation.provider.AnnotationProvider;
import io.d2a.eeee.generate.random.RandomFactory;
import io.d2a.eeee.inject.Inject;
import io.d2a.eeee.inject.Injector;
import io.d2a.eeee.prompt.Wrappers;
import java.lang.reflect.Proxy;
import java.util.Scanner;

public class PromptFactory {

    @SuppressWarnings("unchecked")
    public static <T> T build(final Class<T> clazz, final Injector injector) {
        // get scanner
        final Scanner scanner = injector.find(Scanner.class);
        return (T) Proxy.newProxyInstance(
            clazz.getClassLoader(),
            new Class[]{clazz},
            (proxy, method, args) -> requestAll(
                scanner == null ? new Scanner(System.in) : scanner,
                method::getAnnotation,
                injector,
                method.getReturnType(),
                method.getName()
            )
        );
    }

    public static <T> T build(final Class<T> clazz) {
       return build(clazz, Injector.EMPTY);
    }

    public static Object requestAll(
        final Scanner scanner,
        final AnnotationProvider provider,
        final Injector injector,
        final Class<?> parameterType,
        final String defaultName
    ) throws Exception {

        // using Factory
        if (provider.get(Factory.class) != null) {
            return build(parameterType, injector);
        }

        // using Injector
        final Inject inject = provider.get(Inject.class);
        if (inject != null) {
            // get value from injector
            if (inject.create()) {
                // create new class
                return injector.create(parameterType);
            }
            return injector.find(parameterType, inject.value());
        }

        // using Generate
        final Generate generate = provider.get(Generate.class);
        if (generate != null) {
            final Use use = provider.get(Use.class);
            final Class<?> generatorType;
            if (use != null && !parameterType.isArray()) {
                generatorType = use.value();
            } else {
                generatorType = parameterType;
            }

            return RandomFactory.generate(generatorType, generate.value(), provider, injector);
        }

        // using Prompt
        final Prompt prompt = provider.get(Prompt.class);
        final String displayPrompt = prompt == null ? defaultName : prompt.value();

        // get scanner using injector or create new scanner
        return Wrappers.requestValue(
            scanner,
            parameterType,
            displayPrompt,
            provider
        );
    }

}
