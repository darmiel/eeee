package io.d2a.eeee.generate.placeholder.generators;

import io.d2a.eeee.generate.placeholder.NumberGenerator;
import java.util.Random;

public class RandomDoubleGenerator extends NumberGenerator<Double> {

    @Override
    public Double number(final Random random, final double n) {
        return n;
    }

}
