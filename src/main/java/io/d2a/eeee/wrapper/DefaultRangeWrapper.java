package io.d2a.eeee.wrapper;

import io.d2a.eeee.annotations.provider.AnnotationProvider;
import io.d2a.eeee.annotations.Annotations;
import io.d2a.eeee.annotations.parameters.number.Max;
import io.d2a.eeee.annotations.parameters.number.Min;
import io.d2a.eeee.prompt.Prompt;
import java.util.Scanner;

public abstract class DefaultRangeWrapper<T> implements Wrapper<T> {

    public abstract T wrapValue(final String def);

    ///

    public boolean testRange(final T t, final Double min, final Double max) {
        return true;
    }

    public boolean testAnnotation(final T t, final AnnotationProvider provider) {
        return true;
    }

    ///

    public abstract Prompt prompt();

    @Override
    public T wrap(final Scanner scanner, final String prompt, final AnnotationProvider provider) {
        final Double min = Annotations.get(Min.class, provider);
        final Double max = Annotations.get(Max.class, provider);
        final String def = Annotations.def(provider);

        final String displayPrompt = this.prompt().prompt(provider, prompt, def);

        while (true) {
            System.out.print(displayPrompt);
            final String input = scanner.nextLine().trim();

            // default value
            if (def != null && input.length() == 0) {
                // return default
                return this.wrapValue(def);
            }

            try {
                final T d = this.wrapValue(input);
                if (!this.testRange(d, min, max) || !this.testAnnotation(d, provider)) {
                    continue;
                }
                return d;
            } catch (final Exception ignored) {

            }
        }
    }
}
