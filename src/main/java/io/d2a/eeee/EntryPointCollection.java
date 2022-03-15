package io.d2a.eeee;

import io.d2a.eeee.annotation.annotations.prompt.Entrypoint;
import io.d2a.eeee.annotation.annotations.prompt.ForceRun;
import io.d2a.eeee.converter.StringConverter;
import io.d2a.eeee.inject.Injector;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Stream;

public class EntryPointCollection {

    private final Scanner scanner;
    private final Injector injector;
    private final List<EntryMethod> methods;
    private final Map<Class<?>, Object> instances;

    public EntryPointCollection(
        final Scanner scanner,
        final Injector injector,
        final List<EntryMethod> methods
    ) {
        this.scanner = scanner;
        this.injector = injector;
        this.methods = methods;

        this.instances = new HashMap<>();

        // register EPC in injector
        this.injector.register(EntryPointCollection.class, this);
    }

    public EntryMethod findForcedEntryPoint() {
        return this.methods.stream()
            .filter(c -> c.method.isAnnotationPresent(ForceRun.class))
            .findFirst().orElse(null);
    }

    public EntryMethod select() {
        return this.select(true);
    }

    public EntryMethod select(final boolean withForced) {
        // if there's a forced method, return that
        if (withForced) {
            // if there's only 1 method to run, just select the first
            EntryMethod firstStart = null;
            for (final EntryMethod method : this.methods) {
                if (!method.entrypoint.show() &&
                    this.isCalledEntrypoint(method.entrypoint.value())) {
                    continue;
                }
                if (firstStart == null) {
                    firstStart = method;
                } else {
                    firstStart = null;
                    break;
                }
            }
            if (firstStart != null) {
                return firstStart;
            }

            final EntryMethod forced = this.findForcedEntryPoint();
            if (forced != null) {
                return forced;
            }
        }

        while (true) {
            System.out.println(this); // show prompt
            System.out.printf("🪄 Select Method to run [1-%d/{name}]: ", this.methods.size());

            final String line = scanner.nextLine().trim();
            try {
                // by index
                final int i = Integer.parseInt(line);
                if (i > 0 && i <= this.methods.size()) { // check if number is in range
                    return this.methods.get(i - 1);
                }
            } catch (final NumberFormatException ignored) {
                // or by name
                for (final EntryMethod method : this.methods) {
                    if (method.entrypoint.value().equalsIgnoreCase(line)
                        || method.method.getName().equalsIgnoreCase(line)) {
                        return method;
                    }
                }
            }

            System.out.println("🥊 method not found.");
        }
    }

    public EntryMethod select(final String name) {
        return this.methods.stream()
            .filter(m -> m.entrypoint.value().equals(name))
            .findFirst().orElse(null);
    }

    public Object findInstance(final EntryMethod method) throws Exception {
        // find existing instance for entry method class or create new one using injector
        if (this.instances.containsKey(method.clazz)) {
            return this.instances.get(method.clazz);
        }
        final Object instance = injector.create(method.clazz);
        this.instances.put(method.clazz, instance);
        return instance;
    }

    public Object invoke(final EntryMethod method) throws Exception {
        return method.invoke(this);
    }

    private boolean isCalledEntrypoint(final String name) {
        return this.methods.stream()
            .flatMap((Function<EntryMethod, Stream<Parameter>>)
                method -> Stream.of(method.method.getParameters()))
            .filter(p -> p.isAnnotationPresent(Entrypoint.class))
            .anyMatch(p -> p.getAnnotation(Entrypoint.class).value().equals(name));
    }

    @Override
    public String toString() {
        final StringBuilder bob = new StringBuilder();
        for (int i = 0; i < this.methods.size(); i++) {
            final EntryMethod entry = this.methods.get(i);

            if (!entry.entrypoint.show()) {
                if (this.isCalledEntrypoint(entry.entrypoint.value())) {
                    continue;
                }
            }

            if (i != 0) {
                bob.append('\n');
            }
            bob.append(String.format("%d. %s::%s@%s (%s)",
                i + 1,
                entry.clazz.getSimpleName(),
                entry.entrypoint.value(),
                entry.method.getName(),
                StringConverter.formatTypes(entry.method.getParameterTypes(), false)
            ));
        }
        return bob.toString();
    }

    public Scanner getScanner() {
        return scanner;
    }

    public Injector getInjector() {
        return injector;
    }

}
