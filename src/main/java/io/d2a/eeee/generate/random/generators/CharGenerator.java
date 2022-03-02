package io.d2a.eeee.generate.random.generators;

import io.d2a.eeee.annotation.provider.AnnotationProvider;
import java.util.Random;

public class CharGenerator extends MinMaxGenerator<Character> {

    @Override
    public Character generate(
        final Random random,
        final double min,
        final double max,
        final double step,
        final AnnotationProvider provider
    ) {
        final int mini = (int) min;
        final int maxi = (int) max;
        if (mini == maxi) {
            return (char) mini;
        }
        int n = mini + random.nextInt(maxi - mini);
        if (step != 0) {
            n -= (n - mini) % step;
        }
        return (char) n;
    }

}
