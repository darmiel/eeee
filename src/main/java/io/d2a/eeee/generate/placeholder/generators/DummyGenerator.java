package io.d2a.eeee.generate.placeholder.generators;

import io.d2a.eeee.annotations.provider.AnnotationProvider;
import io.d2a.eeee.generate.placeholder.Generator;
import java.util.Random;

public class DummyGenerator implements Generator<Object> {

    @Override
    public Object generate(final Random random, final AnnotationProvider provider) {
        return null;
    }

}
