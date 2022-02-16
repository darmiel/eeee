package io.d2a.eeee.wrapper.wrappers;

import io.d2a.eeee.annotations.Annotations;
import io.d2a.eeee.prompt.NamedRangePrompt;
import io.d2a.eeee.prompt.Prompt;
import io.d2a.eeee.wrapper.DefaultRangeWrapper;

public class DoubleWrapper extends DefaultRangeWrapper<Double> {

    @Override
    public Double wrapValue(final String def) {
        if (def.length() == 0) {
            return 0D;
        }
        return Double.parseDouble(def);
    }

    @Override
    public boolean testRange(final Double val, final Double min, final Double max) {
        return Annotations.testRange(min, max, val);
    }

    @Override
    public Prompt prompt() {
        return NamedRangePrompt.of("double");
    }

}
