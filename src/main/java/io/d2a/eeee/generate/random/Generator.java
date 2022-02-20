package io.d2a.eeee.generate.random;

import io.d2a.eeee.annotation.provider.AnnotationProvider;
import java.util.Random;

public interface Generator<T> {

    T generate(final Random random, final AnnotationProvider provider, final Class<T> clazz) throws Exception;

}
