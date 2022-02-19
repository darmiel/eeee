package io.d2a.eeee.generate.random.generators;

import io.d2a.eeee.annotation.Annotations;
import io.d2a.eeee.annotation.DefaultAnnotations;
import io.d2a.eeee.annotation.annotations.Range;
import io.d2a.eeee.annotation.provider.AnnotationProvider;
import io.d2a.eeee.generate.random.Generator;
import java.lang.annotation.Annotation;
import java.util.Random;

public abstract class MinMaxGenerator<T> implements Generator<T> {

    public abstract T generate(
        final Random random,
        final double min,
        final double max,
        final AnnotationProvider provider
    );

    @Override
    public T generate(final Random random, final AnnotationProvider provider) {
        final Range range = provider.get(Range.class, DefaultAnnotations.DEFAULT_RANGE);

        final double min = Annotations.getRangeMin(range.value());
        double max = Annotations.getRangeMax(range.value());

        if (max < min) {
            max += min;
        }

        return this.generate(random, min, max, provider);
    }

}
