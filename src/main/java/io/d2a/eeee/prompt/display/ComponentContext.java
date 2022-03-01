package io.d2a.eeee.prompt.display;

import io.d2a.eeee.annotation.provider.AnnotationProvider;

public class ComponentContext {

    private final AnnotationProvider provider;
    private final String display;
    private final Class<?> type;

    public ComponentContext(
        final AnnotationProvider provider,
        final String display,
        final Class<?> type
    ) {
        this.provider = provider;
        this.display = display;
        this.type = type;
    }

    public AnnotationProvider getProvider() {
        return provider;
    }

    public Class<?> getType() {
        return type;
    }

    public String getDisplay() {
        return display;
    }
}
