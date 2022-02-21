package io.d2a.eeee.generate.random.generators;

import io.d2a.eeee.annotation.annotations.Fill;
import io.d2a.eeee.annotation.annotations.Generate;
import io.d2a.eeee.annotation.annotations.Use;
import io.d2a.eeee.annotation.provider.AnnotationProvider;
import io.d2a.eeee.generate.random.Generator;
import io.d2a.eeee.generate.random.RandomFactory;
import java.lang.reflect.Array;
import java.util.Random;

public class ArrayGenerator implements Generator<Object> {

    @Override
    public Object generate(
        final Random random,
        final AnnotationProvider provider,
        final Class<Object> clazz
    ) throws Exception {

        final Fill fill = provider.get(Fill.class);
        final Use use = provider.get(Use.class);

        final int size = fill != null ? fill.value() : 0;

        final Object array = Array.newInstance(clazz.getComponentType(), size);

        final Generate generate = provider.get(Generate.class);

        // fill array?
        if (size > 0) {
            final Class<?> generatorType = use != null ? use.value() : clazz.getComponentType();
            for (int i = 0; i < size; i++) {
                final Object val = RandomFactory.generate(
                    generatorType,
                    generate != null ? generate.value() : null,
                    provider
                );
                Array.set(array, i, val);
            }
        }

        return array;
    }

}
