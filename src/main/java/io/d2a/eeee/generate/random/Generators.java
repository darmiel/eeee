package io.d2a.eeee.generate.random;

import io.d2a.eeee.generate.random.generators.ArrayGenerator;
import io.d2a.eeee.generate.random.generators.BooleanGenerator;
import io.d2a.eeee.generate.random.generators.CharGenerator;
import io.d2a.eeee.generate.random.generators.DoubleGenerator;
import io.d2a.eeee.generate.random.generators.IntGenerator;
import io.d2a.eeee.generate.random.generators.StringGenerator;
import io.d2a.eeee.generate.random.generators.special.NameGenerator;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Generators {

    public static final Generator<Integer> INT = new IntGenerator();
    public static final Generator<Double> DOUBLE = new DoubleGenerator();
    public static final Generator<String> STRING = new StringGenerator();
    public static final Generator<Object> ARRAY = new ArrayGenerator();
    public static final Generator<Character> CHAR = new CharGenerator();
    public static final Generator<Boolean> BOOL = new BooleanGenerator();

    public static final Generator<String> NAME = new NameGenerator();

    public static final Map<Class<?>, Generator<?>> GENERATORS = new HashMap<>();

    static {
        GENERATORS.put(Double.class, DOUBLE);
        GENERATORS.put(double.class, DOUBLE);
        GENERATORS.put(Integer.class, INT);
        GENERATORS.put(int.class, INT);
        GENERATORS.put(String.class, STRING);
        GENERATORS.put(char.class, CHAR);
        GENERATORS.put(Character.class, CHAR);
        GENERATORS.put(boolean.class, BOOL);
        GENERATORS.put(Boolean.class, BOOL);
    }

    @SuppressWarnings("unchecked")
    public static <T> Generator<T> findGenerator(final Class<T> clazz) throws
        NoSuchMethodException,
        InvocationTargetException,
        InstantiationException,
        IllegalAccessException {

        if (clazz.isArray()) {
            return (Generator<T>) ARRAY;
        }

        Generator<T> generator = (Generator<T>) Generators.GENERATORS.get(clazz);
        if (generator == null) {
            if (Generator.class.isAssignableFrom(clazz)) {
                final Constructor<T> constructor = clazz.getDeclaredConstructor();
                constructor.setAccessible(true);
                generator = (Generator<T>) constructor.newInstance();
                Generators.GENERATORS.put(clazz, generator);
            } else {
                // find inherited generator
                for (final Entry<Class<?>, Generator<?>> entry : GENERATORS.entrySet()) {
                    if (entry.getKey().isAssignableFrom(clazz)) {
                        return (Generator<T>) entry.getValue();
                    }
                }
            }
        }

        return generator;
    }

}
