package io.d2a.eeee.generate.random;

import io.d2a.eeee.generate.random.generators.DoubleGenerator;
import io.d2a.eeee.generate.random.generators.IntGenerator;
import io.d2a.eeee.generate.random.generators.special.NameGenerator;
import io.d2a.eeee.generate.random.generators.StringGenerator;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class Generators {

    public static final Generator<Integer> INT = new IntGenerator();
    public static final Generator<Double> DOUBLE = new DoubleGenerator();
    public static final Generator<String> STRING = new StringGenerator();
    public static final Generator<String> NAME = new NameGenerator();

    public static final Map<Class<?>, Generator<?>> GENERATORS = new HashMap<>();

    static {
        GENERATORS.put(Double.class, DOUBLE);
        GENERATORS.put(double.class, DOUBLE);
        GENERATORS.put(Integer.class, INT);
        GENERATORS.put(int.class, INT);
        GENERATORS.put(String.class, STRING);

    }

    @SuppressWarnings("unchecked")
    public static <T> Generator<T> findGenerator(final Class<T> clazz) throws
        NoSuchMethodException,
        InvocationTargetException,
        InstantiationException,
        IllegalAccessException {

        Generator<T> generator = (Generator<T>) Generators.GENERATORS.get(clazz);
        if (generator == null) {
            if (Generator.class.isAssignableFrom(clazz)) {
                final Constructor<T> constructor = clazz.getDeclaredConstructor();
                constructor.setAccessible(true);
                generator = (Generator<T>) constructor.newInstance();
                Generators.GENERATORS.put(clazz, generator);
            }
        }

        return generator;
    }

}
