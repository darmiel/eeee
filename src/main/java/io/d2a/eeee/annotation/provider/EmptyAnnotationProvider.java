package io.d2a.eeee.annotation.provider;

import java.lang.annotation.Annotation;

public class EmptyAnnotationProvider implements AnnotationProvider {

    public static final EmptyAnnotationProvider DEFAULT = new EmptyAnnotationProvider();

    @Override
    public <A extends Annotation> A get(final Class<A> clazz) {
        return null;
    }

}
