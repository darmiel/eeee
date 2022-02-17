package io.d2a.eeee.generate.placeholder;

import io.d2a.eeee.annotations.AnnotationProvider;
import io.d2a.eeee.annotations.Annotations;
import io.d2a.eeee.annotations.parameters.number.Max;
import io.d2a.eeee.annotations.parameters.number.Min;
import java.util.Random;

public abstract class NumberGenerator<T> implements Generator<T> {

    public abstract T number(final Random random, final double num);

    public double defaultMin() {
        return 0;
    }

    public double defaultMax() {
        return 10;
    }

    @Override
    public T generate(final Random random, final AnnotationProvider provider) {
        final Double min = Annotations.get(Min.class, provider);
        final Double max = Annotations.get(Max.class, provider);

        double minVal = this.defaultMin();
        if (min != null) {
            minVal = min;
        }
        double maxVal = this.defaultMax();
        if (max != null) {
            maxVal = max;
        }

        if (minVal > maxVal && maxVal == this.defaultMax()) {
            maxVal += minVal;
        }

        return this.number(
            random,
            (random.nextDouble() * (maxVal - minVal)) + minVal
        );
    }

}
