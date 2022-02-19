package io.d2a.eeee.annotation;

import io.d2a.eeee.annotation.provider.AnnotationProvider;
import io.d2a.eeee.annotation.annotations.Default;

public class Annotations {

    public static double getRangeMin(final double[] value) {
        /*
         * {} -> 0
         * {10} -> 0
         * {1, 10} -> 10
         */
        switch (value.length) {
            case 0:
            case 1:
                return 0;
            default:
                return value[0];
        }
    }

    public static double getRangeMax(final double[] value) {
        /*
         * {} -> 10
         * {5} -> 5
         * {1, 5} -> 1
         */
        switch (value.length) {
            case 0:
                return 10;
            case 1:
                return value[0];
            default:
                return value[1];
        }
    }

    public static double getRangeStep(final double[] value) {
        switch (value.length) {
            case 0:
            case 1:
            case 2:
                return 0;
            default:
                return value[2];
        }
    }

    public static boolean testRange(final double min, final double max, final double step, final double input) {
        if (input < min || input > max) {
            return false;
        }
        if (step != 0) {
            return input % step == 0;
        }
        return true;
    }

    public static String def(final AnnotationProvider provider) {
        final Default def = provider.get(Default.class);
        if (def != null) {
            return def.value();
        }
        return null;
    }


}
