package io.d2a.eeee.annotation;

import io.d2a.eeee.annotation.annotations.common.Range;
import java.lang.annotation.Annotation;

public class DefaultAnnotations {

    public static final Range DEFAULT_RANGE = new Range() {
        @Override
        public Class<? extends Annotation> annotationType() {
            return Range.class;
        }
        @Override
        public double[] value() {
            return new double[] {1, 10};
        }
    };

}
