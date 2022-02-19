package io.d2a.eeee.generate.random.generators;

import io.d2a.eeee.annotation.provider.AnnotationProvider;
import java.util.Random;

public class IntGenerator extends MinMaxGenerator<Integer> {

    @Override
    public Integer generate(
        final Random random,
        final double min,
        final double max,
        final AnnotationProvider provider
    ) {
        final int mini = (int) min;
        final int maxi = (int) max;
        if (mini == maxi) {
            return mini;
        }
        return mini + random.nextInt(maxi - mini);
    }

}
