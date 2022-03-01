package io.d2a.eeee.annotation;

import io.d2a.eeee.annotation.annotations.common.Range;
import io.d2a.eeee.annotation.annotations.prompt.Default;
import io.d2a.eeee.annotation.provider.AnnotationProvider;
import io.d2a.eeee.prompt.exception.ValidateException;
import io.d2a.eeee.prompt.exception.WrapException.Action;

public class Annotations {

    public static double[] getRange(final Range range) {
        if (range == null) {
            return new double[0];
        }
        final double[] value = range.value();

        final double min;
        final double max;
        final double step;

        if (value.length >= 2) {
            min = value[0];
            max = value[1];
            if (value.length >= 3) {
                step = value[2];
            } else {
                step = 0;
            }
        } else {
            return new double[0];
        }

        if (min > max) {
            throw new RuntimeException("min > max");
        }

        return new double[]{
            min, max, step
        };
    }

    public static void checkRange(final double value, final double[] range)
        throws ValidateException {
        if (range.length != 3) {
            return; // nothing to validate
        }
        final double min = range[0];
        if (value < min) {
            throw new ValidateException("input too low", Action.RETRY);
        }
        final double max = range[1];
        if (value > max) {
            throw new ValidateException("input too high 🚬", Action.RETRY);
        }
        final double step = range[2];
        if (step != 0 && (value - min) % step != 0) {
            throw new ValidateException("input not step of " + step, Action.RETRY);
        }
    }

    public static boolean testRange(final double min, final double max, final double step,
        final double input) {
        if (input < min || input > max) {
            return false;
        }
        if (step != 0) {
            return (input - min) % step == 0;
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
