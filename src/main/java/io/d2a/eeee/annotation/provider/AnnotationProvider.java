package io.d2a.eeee.annotation.provider;

import java.lang.annotation.Annotation;

public interface AnnotationProvider {

    <A extends Annotation> A get(final Class<A> clazz);

    default <A extends Annotation> A get(final Class<A> clazz, final A def) {
        final A val = this.get(clazz);
        if (val == null) {
            return def;
        }
        return val;
    }

}
