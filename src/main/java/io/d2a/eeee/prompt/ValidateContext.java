package io.d2a.eeee.prompt;

import io.d2a.eeee.annotation.provider.AnnotationProvider;
import java.lang.annotation.Annotation;

public class ValidateContext {

    private final AnnotationProvider provider;

    public ValidateContext(final AnnotationProvider provider) {
        this.provider = provider;
    }

    public <A extends Annotation> A a(final Class<A> clazz) {
        return this.provider.get(clazz);
    }

}
