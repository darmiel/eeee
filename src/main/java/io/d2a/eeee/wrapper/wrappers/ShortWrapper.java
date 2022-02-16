package io.d2a.eeee.wrapper.wrappers;

import io.d2a.eeee.annotations.Annotations;
import io.d2a.eeee.prompt.NamedRangePrompt;
import io.d2a.eeee.prompt.Prompt;
import io.d2a.eeee.wrapper.DefaultRangeWrapper;

public class ShortWrapper extends DefaultRangeWrapper<Short> {

    @Override
    public Short wrapValue(final String def) {
        if (def.length() == 0) {
            return (short) 0;
        }
        return Short.parseShort(def);
    }

    @Override
    public boolean testRange(final Short val, final Double min, final Double max) {
        return Annotations.testRange(min, max, val);
    }

    @Override
    public Prompt prompt() {
        return NamedRangePrompt.of("short");
    }

}
