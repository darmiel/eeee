package io.d2a.eeee.generate.random.generators;

import io.d2a.eeee.annotation.provider.AnnotationProvider;
import java.util.Random;

public class DoubleGenerator extends MinMaxGenerator<Double> {

    @Override
    public Double generate(
        final Random random,
        final double min,
        final double max,
        final double step,
        final AnnotationProvider provider
    ) {
        if (min == max) {
            return min;
        }
        double n = min + (max - min) * random.nextDouble();
        if (step != 0) {
            n -= (n - min) % step;
        }
        return n;
    }

}
