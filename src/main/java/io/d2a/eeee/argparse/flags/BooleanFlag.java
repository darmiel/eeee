package io.d2a.eeee.argparse.flags;

import io.d2a.eeee.argparse.Flag;
import io.d2a.eeee.prompt.wrappers.BoolWrapper;

public class BooleanFlag extends Flag<Boolean> {

    public BooleanFlag(final String key) {
        super(key);
    }

    @Override
    public Boolean wrap(final String input) throws Exception {
        return BoolWrapper.YES.contains(input.trim().toLowerCase());
    }

}
