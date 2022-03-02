package io.d2a.eeee.generate.random.generators;

import io.d2a.eeee.annotation.provider.AnnotationProvider;
import io.d2a.eeee.generate.random.Generator;
import java.util.Random;

public class BooleanGenerator implements Generator<Boolean> {

    @Override
    public Boolean generate(
        final Random random,
        final AnnotationProvider provider,
        final Class<Boolean> clazz
    ) throws Exception {
        return random.nextBoolean();
    }

}
