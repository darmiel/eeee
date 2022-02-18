package io.d2a.eeee.annotations.provider;

import java.lang.annotation.Annotation;

public class AnyAnnotationProvider implements AnnotationProvider {

    private final AnnotationProvider[] providers;

    public AnyAnnotationProvider(final AnnotationProvider ... providers) {
        this.providers = providers;
    }

    @Override
    public <A extends Annotation> A get(final Class<A> clazz) {
        for (final AnnotationProvider provider : this.providers) {
            final A a = provider.get(clazz);
            if (a != null) {
                return a;
            }
        }
        return null;
    }

}
