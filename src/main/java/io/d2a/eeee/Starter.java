package io.d2a.eeee;

import io.d2a.eeee.annotation.annotations.prompt.Entrypoint;
import io.d2a.eeee.annotation.annotations.prompt.ForceRun;
import io.d2a.eeee.inject.Injector;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

public class Starter {

    public static void startUnsafe(final Class<?> clazz, final String[] args) {
        try {
            start(clazz, args);
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
        start(clazz, args, null);
    }

    public static void start(
        final Class<?> clazz,
        final String[] args,
        final Consumer<Injector> injectorSupplier
    ) throws Exception {
        final Scanner scanner = new Scanner(System.in);

        final Injector injector = new Injector()
            .register(Scanner.class, scanner)
            .register(String[].class, args, "args");
        injector.register(Injector.class, injector); // register self

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
            System.out.println("Error: cannot find entrypoint in class.");
            return;
        }

        // order list by method name
        methods.sort((o1, o2) -> String.CASE_INSENSITIVE_ORDER.compare(
            o1.method.getName(),
            o2.method.getName()
        ));

        // auto select first method if only one method exists in that class
        EntryMethod config = getForcedConfig(methods);
        if (config == null && (config = select(clazz, scanner, methods)) == null) {
            System.out.println("Invalid run config.");
            return;
        }

        config.invoke(scanner, injector);
    }

    ///

    private static EntryMethod getForcedConfig(final List<? extends EntryMethod> list) {
        for (final EntryMethod config : list) {
            if (config.method.isAnnotationPresent(ForceRun.class)) {
                return config;
            }
        }
        return null;
    }

    private static void printConfigs(final Class<?> clazz, final List<? extends EntryMethod> list) {
        // print method selection
        int i = 0;
        for (final EntryMethod method : list) {
            // print method number and name
            System.out.printf("%d. %s::%s@%s (%s)%n",
                ++i,
                clazz.getSimpleName(),
                method.entrypoint.value(),
                method.method.getName(),
                formatTypes(method.method.getParameterTypes(), false)
            );
        }
    }

    private static EntryMethod select(final Class<?> clazz, final Scanner scanner,
        final List<? extends EntryMethod> list) {
        // if there's only 1 method to run, just select the first
        if (list.size() == 1) {
            return list.get(0);
        }
        while (true) {
            printConfigs(clazz, list);
            System.out.printf("[?] Select Method to run [1-%d]: ", list.size());

            final String line = scanner.nextLine().trim();
            try {
                final int i = Integer.parseInt(line);
                if (i > 0 && i <= list.size()) {
                    return list.get(i - 1);
                }
            } catch (NumberFormatException ignored) {
            }
        }
    }

    public static String formatTypes(final Class<?>[] types, boolean sh) {
        final StringBuilder bob = new StringBuilder();
        for (final Class<?> type : types) {
            if (bob.length() > 0) {
                bob.append(",");
            }
            if (sh) {
                bob.append(type.getSimpleName().charAt(0));
            } else {
                bob.append(type.getSimpleName());
            }
        }
        return bob.toString();
    }

}
