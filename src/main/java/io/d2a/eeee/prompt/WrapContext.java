package io.d2a.eeee.prompt;

import io.d2a.eeee.annotation.provider.AnnotationProvider;
import java.lang.annotation.Annotation;
import java.util.Scanner;

public class WrapContext {

    private final Scanner scanner;
    private final Class<?> type;
    private final AnnotationProvider provider;

    public WrapContext(final Scanner scanner, final Class<?> type,
        final AnnotationProvider provider) {
        this.scanner = scanner;
        this.type = type;
        this.provider = provider;
    }

    public Scanner getScanner() {
        return scanner;
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
