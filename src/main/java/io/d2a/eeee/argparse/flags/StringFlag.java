package io.d2a.eeee.argparse.flags;

import io.d2a.eeee.argparse.Flag;

public class StringFlag extends Flag<String> {

    public StringFlag(final String key) {
        super(key);
    }

    @Override
    public String wrap(final String input) {
        return input;
    }

}
