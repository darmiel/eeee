package io.d2a.eeee.generate.placeholder.generators;

import io.d2a.eeee.generate.placeholder.NumberGenerator;
import java.util.Random;

public class RandomIntGenerator extends NumberGenerator<Integer> {

    @Override
    public Integer number(final Random random, final double n) {
        return (int) n;
    }

}
