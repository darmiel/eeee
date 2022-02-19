package io.d2a.eeee.wrapper;

import io.d2a.eeee.annotation.DefaultAnnotations;
import io.d2a.eeee.annotation.annotations.Range;
import io.d2a.eeee.annotation.provider.AnnotationProvider;
import io.d2a.eeee.annotation.Annotations;
import io.d2a.eeee.prompt.Prompt;
import java.util.Scanner;

public abstract class DefaultRangeWrapper<T> implements Wrapper<T> {

    public abstract T wrapValue(final String def, final AnnotationProvider provider);

    ///

    public boolean testRange(final T t, final double min, final double max, final double step) {
        return true;
    }

    ///

    public abstract Prompt prompt();

    @Override
    public T wrap(final Scanner scanner, final String prompt, final AnnotationProvider provider) {
        final Range range = provider.get(Range.class, DefaultAnnotations.DEFAULT_RANGE);
        final boolean checkRange = provider.get(Range.class) != null;

        final double min = Annotations.getRangeMin(range.value());
        final double step = Annotations.getRangeStep(range.value());
        double max = Annotations.getRangeMax(range.value());

        if (max < min) {
            max += min;
        }

        final String def = Annotations.def(provider);
        final String displayPrompt = this.prompt().prompt(provider, prompt, def);

        while (true) {
            System.out.print(displayPrompt);
            final String input = scanner.nextLine().trim();

            // default value
            if (def != null && input.length() == 0) {
                // return default
                return this.wrapValue(def, provider);
            }

            try {
                final T d = this.wrapValue(input, provider);
                if (checkRange && !this.testRange(d, min, max, step)) {
                    continue;
                }
                return d;
            } catch (final Exception ignored) {

            }
        }
    }
}
