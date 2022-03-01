package io.d2a.eeee;

import io.d2a.eeee.annotation.annotations.prompt.Entrypoint;
import io.d2a.eeee.annotation.provider.PriorityAnnotationProvider;
import io.d2a.eeee.inject.Injector;
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

    public void invoke(final Scanner scanner, final Injector injector) throws Exception {
        final String typeStr = Starter.formatTypes(method.getParameterTypes(), true);

        // create starter class using injector
        final Object instance = injector.create(this.clazz);

        do {
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
                    // execute wrapper
                    parameters.add(PromptFactory.requestAll(
                        scanner,
                        new PriorityAnnotationProvider(
                            method::getAnnotation,
                            parameter::getAnnotation
                        ),
                        injector,
                        parameter.getType(),
                        parameter.getName()
                    ));
                }
            }
            System.out.println();

            if (this.entrypoint.verbose()) {
                // invoke method
                System.out.printf("[Runner] Invoking %s@%s w/ (%s)...%n",
                    method.getName(), clazz.getSimpleName(), typeStr);
            }

            if (this.entrypoint.verbose() || this.entrypoint.stopwatch()) {
                System.out.println("---");
            }

            final long timeStart = System.currentTimeMillis();
            this.method.invoke(instance, parameters.toArray());
            final long timeEnd = System.currentTimeMillis();

            if (this.entrypoint.verbose() || this.entrypoint.stopwatch()) {
                System.out.println("---");
            }

            if (this.entrypoint.stopwatch()) {
                System.out.printf("Execution complete! Took approx. %dms.%n", timeEnd - timeStart);
            }
        } while (this.entrypoint.loop());
    }

}
