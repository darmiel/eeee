package io.d2a.eeee.nw;

import io.d2a.eeee.annotation.provider.AnnotationProvider;
import java.lang.annotation.Annotation;

public class WrapContext {

    private final Class<?> type;
    private final AnnotationProvider provider;

    public WrapContext(final Class<?> type, final AnnotationProvider provider) {
        this.type = type;
        this.provider = provider;
    }

    public Class<?> getType() {
        return type;
    }

    public Object getProvider() {
        return provider;
    }

    public <A extends Annotation> A a(final Class<A> clazz) {
        return this.provider.get(clazz);
    }

}
