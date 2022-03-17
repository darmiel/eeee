package io.d2a.eeee.generate.random.generators;

import io.d2a.eeee.annotation.annotations.common.Use;
import io.d2a.eeee.annotation.annotations.generate.Fill;
import io.d2a.eeee.annotation.annotations.generate.FillRange;
import io.d2a.eeee.annotation.annotations.generate.Generate;
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

        final Use use = provider.get(Use.class);

        // get fill size from annotations
        final Fill fill = provider.get(Fill.class);
        final FillRange fillRange = provider.get(FillRange.class);
        final int size;
        if (fill != null) { // @Fill has the highest priority
            size = fill.value();
        } else if (fillRange != null) { // @FillRaneg has the second priority
            final int[] range = fillRange.value();
            final int min, max;
            if (range.length == 2) {
                min = range[0];
                max = range[1];
            } else if (range.length == 1 ) {
                min = 0;
                max = range[0];
            } else {
                throw new IllegalArgumentException("invalid fill range");
            }
            size = random.nextInt((max + 1) - min) + min;
        } else  { // default is 0
            size = 0;
        }

        final Object array = Array.newInstance(clazz.getComponentType(), size);
        final Generate generate = provider.get(Generate.class);

        if (size > 0) {
            final Class<?> generatorType;
            if (use != null ) {
               generatorType = use.value();
            } else {
               generatorType = clazz.getComponentType();
            }
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
