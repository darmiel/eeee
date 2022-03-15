package io.d2a.eeee.argparse.flags;

import io.d2a.eeee.argparse.Flag;

public class DoubleFlag extends Flag<Double> {

    public DoubleFlag(final String key) {
       super(key);
    }

    @Override
    public Double wrap(final String input) throws Exception {
        return Double.parseDouble(input);
    }

}
