package io.d2a.eeee.annotation.provider;

import java.lang.annotation.Annotation;

public class PriorityAnnotationProvider implements AnnotationProvider {

    private final AnnotationProvider[] providers;

    public PriorityAnnotationProvider(final AnnotationProvider ... providers) {
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
