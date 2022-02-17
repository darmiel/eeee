package io.d2a.eeee.generate.placeholder;

import io.d2a.eeee.generate.placeholder.generators.RandomDoubleGenerator;
import io.d2a.eeee.generate.placeholder.generators.RandomIntGenerator;
import io.d2a.eeee.generate.placeholder.generators.RandomStringGenerator;
import java.util.HashMap;
import java.util.Map;

public class Generators {

    public static final Map<Class<?>, Generator<?>> GENERATORS = new HashMap<>();

    static {
        final RandomDoubleGenerator doubleGenerator = new RandomDoubleGenerator();
        GENERATORS.put(Double.class, doubleGenerator);
        GENERATORS.put(double.class, doubleGenerator);

        final RandomIntGenerator intGenerator = new RandomIntGenerator();
        GENERATORS.put(Integer.class, intGenerator);
        GENERATORS.put(int.class, intGenerator);

        GENERATORS.put(String.class, new RandomStringGenerator());
    }

}
