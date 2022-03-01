package io.d2a.eeee.generate.random.generators;

import io.d2a.eeee.annotation.Annotations;
import io.d2a.eeee.annotation.DefaultAnnotations;
import io.d2a.eeee.annotation.annotations.common.Range;
import io.d2a.eeee.annotation.provider.AnnotationProvider;
import io.d2a.eeee.generate.random.Generator;
import java.util.Random;

public abstract class MinMaxGenerator<T> implements Generator<T> {

    public abstract T generate(
        final Random random,
        final double min,
        final double max,
        final double step,
        final AnnotationProvider provider
    );

    @Override
    public T generate(final Random random, final AnnotationProvider provider, final Class<T> clazz) {
        final Range range = provider.get(Range.class, DefaultAnnotations.DEFAULT_RANGE);
        final double[] ranges = Annotations.getRange(range);

        return this.generate(random, ranges[0], ranges[1] + 1, ranges[2], provider);
    }

}
