package io.d2a.eeee.generate.placeholder.generators;

import io.d2a.eeee.generate.placeholder.NumberGenerator;
import java.util.Random;

public class RandomStringGenerator extends NumberGenerator<String> {

    public static final char[] CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
        .toCharArray();

    @Override
    public String number(final Random random, final double n) {
        final StringBuilder bob = new StringBuilder();
        for (int i = 0; i < (int) n; i++) {
            bob.append(CHARS[random.nextInt(CHARS.length)]);
        }
        return bob.toString();
    }

}
