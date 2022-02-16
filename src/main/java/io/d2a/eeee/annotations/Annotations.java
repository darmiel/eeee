package io.d2a.eeee.annotations;

import io.d2a.eeee.annotations.parameters.number.Max;
import io.d2a.eeee.annotations.parameters.number.Min;
import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;

public class Annotations {

    public static Double get(final Class<? extends Annotation> clazz,
        final AnnotationProvider provider) {
        if (provider.get(clazz) != null) {
            if (clazz.equals(Min.class)) {
                return provider.get(Min.class).value();
            } else if (clazz.equals(Max.class)) {
                return provider.get(Max.class).value();
            }
        }
        return null;
    }

    public static String range(final Double min, final Double max) {
        return range(min, max, false);
    }

    public static String range(final Double min, final Double max, final boolean i) {
        if (min == null && max == null) {
            return "";
        }
        final StringBuilder bob = new StringBuilder();
        if (min != null) {
            if (i) {
                bob.append(min.intValue());
            } else {
                bob.append(min);
            }
        }
        bob.append('-');
        if (max != null) {
            if (i) {
                bob.append(max.intValue());
            } else {
                bob.append(max);
            }
        }
        return bob.toString();
    }

    public static boolean testRange(final Double min, final Double max, final double input) {
        if (min != null && input < min) {
            return false;
        }
        return max == null || !(input > max);
    }

    public static String generatePrompt(
        final String prompt,
        final String type,
        final Double min,
        final Double max,
        final String def
    ) {
        final String range = Annotations.range(min, max);
        final StringBuilder bob = new StringBuilder();
        bob.append("[").append(type).append("] ");
        if (range.length() > 0) {
            bob.append('[').append(range).append("] ");
        }
        bob.append(prompt);
        if (def != null) {
            bob.append(" [").append(def).append("]");
        }
        return bob.append(": ").toString();
    }

    public static String def(final AnnotationProvider provider) {
        final Default def = provider.get(Default.class);
        if (def != null) {
            return def.value();
        }
        return null;
    }

}
