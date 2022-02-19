package io.d2a.eeee.wrapper.wrappers;

import io.d2a.eeee.annotation.Annotations;
import io.d2a.eeee.annotation.provider.AnnotationProvider;
import io.d2a.eeee.prompt.Prompt;
import io.d2a.eeee.prompt.StackedPrompt;
import io.d2a.eeee.wrapper.DefaultRangeWrapper;

public class DoubleWrapper extends DefaultRangeWrapper<Double> {

    @Override
    public Double wrapValue(final String def, final AnnotationProvider provider) {
        if (def.length() == 0) {
            return 0D;
        }
        return Double.parseDouble(def);
    }

    @Override
    public boolean testRange(final Double val, final double min, final double max, final double step) {
        return Annotations.testRange(min, max, step, val);
    }

    @Override
    public Prompt prompt() {
        return new StackedPrompt("double");
    }

}
