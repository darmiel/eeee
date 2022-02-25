package io.d2a.eeee.nw;

import io.d2a.eeee.annotation.provider.AnnotationProvider;
import io.d2a.eeee.nw.exception.ValidateException;
import io.d2a.eeee.nw.exception.WrapException.Action;
import io.d2a.eeee.nw.wrappers.ArrayWrapper;
import io.d2a.eeee.nw.wrappers.DoubleWrapper;
import io.d2a.eeee.nw.wrappers.IntWrapper;
import io.d2a.eeee.nw.wrappers.StringWrapper;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Wrappers {

    private static final Map<Class<?>, Wrapper<?>> WRAPPERS = new HashMap<>();

    static {
        WRAPPERS.put(Array.class, new ArrayWrapper());

        final DoubleWrapper doubleWrapper = new DoubleWrapper();
        WRAPPERS.put(Double.class, doubleWrapper);
        WRAPPERS.put(double.class, doubleWrapper);

        final IntWrapper intWrapper = new IntWrapper();
        WRAPPERS.put(Integer.class, intWrapper);
        WRAPPERS.put(int.class, intWrapper);

        WRAPPERS.put(String.class, new StringWrapper());
    }

    public static Wrapper<?> findWrapper(final Class<?> type) throws Exception {
        // always return array wrapper
        if (type.isArray()) {
            return WRAPPERS.get(Array.class);
        }

        Wrapper<?> wrapper = WRAPPERS.get(type);
        if (wrapper == null) {
            // has {type} implemented the Wrapper<T> interface?
            if (!Wrapper.class.isAssignableFrom(type)) {
                // try to find matching wrappers
                for (final Entry<Class<?>, Wrapper<?>> entry : WRAPPERS.entrySet()) {
                    if (entry.getKey().isAssignableFrom(type)) {
                        return entry.getValue();
                    }
                }
                // we did our best.
                throw new IllegalArgumentException(
                    "cannot find wrapper for parameter " + type.getSimpleName()
                );
            }
            // yes yes, create new class and add to Wrappers
            final Constructor<?> wrapperConstructor = type.getDeclaredConstructor();
            wrapperConstructor.setAccessible(true);
            wrapper = (Wrapper<?>) wrapperConstructor.newInstance();
            Wrappers.WRAPPERS.put(type, wrapper);
        }
        return wrapper;
    }


    @SuppressWarnings("unchecked")
    public static <T> T requestValue(
        final Scanner scanner,
        final Class<?> type,
        final String display,
        final AnnotationProvider provider
    ) throws Exception {
        final Wrapper<T> wrapper = (Wrapper<T>) findWrapper(type);
        final WrapContext ctx = new WrapContext(type, provider);
        final String prompt = wrapper.prompt(ctx).prompt(provider, type, display);

        // print prompt
        while (true) {
            System.out.print(prompt);

            // read input
            final String line = scanner.nextLine().trim();

            // try to wrap value
            final T t;
            try {
                t = wrapper.wrap(line, ctx);
            } catch (final Exception ex) {
                System.out.println(" 🦑 [wrap-error] " + ex.getMessage());
                continue;
            }

            // do we need to validate the input?
            if (wrapper instanceof Validate) {
                final Validate<T> validate = (Validate<T>) wrapper;
                final ValidateContext validateContext = new ValidateContext(provider);
                try {
                    validate.check(t, validateContext);
                } catch (final Exception ex) {
                    System.out.println(" 🥊 [val-error] " + ex.getMessage());

                    if (ex instanceof ValidateException) {
                        final ValidateException vex = (ValidateException) ex;
                        if (vex.getAction() == Action.EXIT) {
                            throw ex;
                        }
                    }

                    continue;
                }
            }

            return t;
        }
    }

}
