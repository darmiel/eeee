package io.d2a.eeee;

import io.d2a.eeee.annotation.annotations.prompt.Entrypoint;
import io.d2a.eeee.annotation.provider.PriorityAnnotationProvider;
import io.d2a.eeee.converter.StringConverter;
import io.d2a.eeee.prompt.Call;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

public class EntryMethod {

    public final Method method;
    public final Entrypoint entrypoint;
    public final Class<?> clazz;

    public EntryMethod(final Method method, final Entrypoint entrypoint, final Class<?> clazz) {
        this.method = method;
        this.entrypoint = entrypoint;
        this.clazz = clazz;
    }

    public Object invoke(final EntryPointCollection epc) throws Exception {
        final String typeStr = StringConverter.formatTypes(method.getParameterTypes(), true);

        // find instance to invoke method in
        final Object instance = epc.findInstance(this);

        // res is returned at the end
        Object res;

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
                    if (Call.class.isAssignableFrom(parameter.getType())) {
                        if (!parameter.isAnnotationPresent(Entrypoint.class)) {
                            throw new IllegalArgumentException("found call type but without entrypoint annotation");
                        }
                        final Entrypoint target = parameter.getAnnotation(Entrypoint.class);
                        final EntryMethod targetMethod = epc.select(target.value());
                        if (targetMethod == null) {
                            throw new IllegalArgumentException("cannot find entrypoint");
                        }
                        parameters.add((Call<Object>) () -> {
                            try {
                                return epc.invoke(targetMethod);
                            } catch (Exception e) {
                                e.printStackTrace();
                                return null;
                            }
                        });
                        continue;
                    }

                    // execute wrapper
                    parameters.add(PromptFactory.requestAll(
                        epc.getScanner(),
                        new PriorityAnnotationProvider(
                            method::getAnnotation,
                            parameter::getAnnotation
                        ),
                        epc.getInjector(),
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
            res = this.method.invoke(instance, parameters.toArray());
            final long timeEnd = System.currentTimeMillis();

            if (this.entrypoint.verbose() || this.entrypoint.stopwatch()) {
                System.out.println("---");
            }

            if (this.entrypoint.stopwatch()) {
                System.out.printf("Execution complete! Took approx. %dms.%n", timeEnd - timeStart);
            }
        } while (this.entrypoint.loop());

        return res;
    }

}
