package io.d2a.eeee.generate.placeholder;

import io.d2a.eeee.annotations.provider.AnnotationProvider;
import java.util.Random;

public interface Generator<T> {

    T generate(final Random random, final AnnotationProvider provider);

}
