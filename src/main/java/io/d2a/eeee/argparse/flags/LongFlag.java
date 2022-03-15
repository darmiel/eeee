package io.d2a.eeee.argparse.flags;

import io.d2a.eeee.argparse.Flag;

public class LongFlag extends Flag<Long> {

    public LongFlag(final String key) {
        super(key);
    }

    @Override
    public Long wrap(final String input) throws Exception {
        return Long.parseLong(input);
    }

}
