package io.d2a.eeee;

import io.d2a.eeee.annotation.annotations.prompt.Entrypoint;
import io.d2a.eeee.inject.Injector;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

public class Starter {

    public static void startUnsafe(final Class<?> clazz, final String[] args) {
        startUnsafe(clazz, args, false);
    }

    public static void startUnsafe(final Class<?> clazz, final String[] args, final boolean loop) {
        try {
            start(clazz, args, loop);
        } catch (final Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void byCaller() {
        final StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        final StackTraceElement last = elements[elements.length - 1];
        final String className = last.getClassName();

        // find class by name
        try {
            final Class<?> clazz = Class.forName(className);
            start(clazz);
        } catch (final Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void start(
        final Class<?> clazz
    ) throws Exception {
        start(clazz, new String[0]);
    }

    public static void start(
        final Class<?> clazz,
        final String[] args
    ) throws Exception {
        start(clazz, args, false);
    }

    public static void start(
        final Class<?> clazz,
        final boolean loop
    ) throws Exception {
        start(clazz, new String[0], loop);
    }

    public static void start(
        final Class<?> clazz,
        final String[] args,
        final boolean loop
    ) throws Exception {
        start(clazz, loop, args, null);
    }

    public static void start(
        final Class<?> clazz,
        final boolean loop,
        final String[] args,
        final Consumer<Injector> injectorSupplier
    ) throws Exception {
        final EntryPointCollection epc = create(clazz, args, injectorSupplier);
        //noinspection LoopConditionNotUpdatedInsideLoop
        do {
            // select method and run
            final EntryMethod method = epc.select();
            epc.invoke(method);
        } while (loop);
    }

    private static EntryPointCollection create(
        final Class<?> clazz,
        final String[] args,
        final Consumer<Injector> injectorSupplier
    ) {
        final Scanner scanner = new Scanner(System.in);

        final Injector injector = new Injector()
            .register(Scanner.class, scanner)
            .register(String[].class, args, "args");

        if (injectorSupplier != null) {
            injectorSupplier.accept(injector);
        }

        final List<EntryMethod> methods = new ArrayList<>();

        // first, find any entrypoint method
        for (final Method method : clazz.getDeclaredMethods()) {
            // check if the method is an entrypoint
            if (!method.isAnnotationPresent(Entrypoint.class)) {
                continue;
            }
            final Entrypoint entrypoint = method.getAnnotation(Entrypoint.class);
            final EntryMethod config = new EntryMethod(
                method, entrypoint, clazz
            );
            methods.add(config);
        }

        // if no method was found, simply exit
        if (methods.size() == 0) {
            throw new IllegalArgumentException("no entrypoint found in class.");
        }

        // order list by method name
        methods.sort((o1, o2) -> String.CASE_INSENSITIVE_ORDER.compare(
            o1.method.getName(),
            o2.method.getName()
        ));

        return new EntryPointCollection(
            scanner,
            injector,
            methods
        );
    }

}
