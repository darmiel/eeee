package io.d2a.eeee.generate.random.generators;

import io.d2a.eeee.annotation.annotations.common.Transform;
import io.d2a.eeee.annotation.annotations.common.Transform.Type;
import io.d2a.eeee.annotation.provider.AnnotationProvider;
import java.util.Random;

public class StringGenerator extends MinMaxGenerator<String> {

    public static final char[] CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
        .toCharArray();

    public String generateString(final Random random, final int length) {
        final StringBuilder bob = new StringBuilder();
        for (int i = 0; i < length; i++) {
            bob.append(CHARS[random.nextInt(CHARS.length)]);
        }
        return bob.toString();
    }

    @Override
    public String generate(
        final Random random,
        final double min,
        final double max,
        final double step,
        final AnnotationProvider provider
    ) {
        int n;

        final int mini = (int) min;
        final int maxi = (int) max;
        if (mini == maxi) {
            n = mini;
        } else {
            n = random.nextInt(maxi - mini) + mini;
        }
        if (step != 0) {
            n -= (n - min) % step;
        }

        String res = generateString(random, n);

        // transform string
        final Transform transform = provider.get(Transform.class);
        if (transform != null) {
            for (final Type type : transform.value()) {
                res = type.transform(res);
            }
        }

        return res;
    }

}
