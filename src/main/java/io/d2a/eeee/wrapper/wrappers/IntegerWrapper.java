package io.d2a.eeee.wrapper.wrappers;

import io.d2a.eeee.annotation.Annotations;
import io.d2a.eeee.annotation.annotations.Range;
import io.d2a.eeee.annotation.provider.AnnotationProvider;
import io.d2a.eeee.prompt.Prompt;
import io.d2a.eeee.prompt.StackedPrompt;
import io.d2a.eeee.wrapper.DefaultRangeWrapper;

public class IntegerWrapper extends DefaultRangeWrapper<Integer> {

    @Override
    public Integer wrapValue(final String def, final AnnotationProvider provider) {
        if (def.length() == 0) {
            return 0;
        }
        return Integer.parseInt(def);
    }

    @Override
    public boolean testRange(final Integer integer, final double min, final double max, final double step) {
        return Annotations.testRange(min, max, step, integer);
    }

    @Override
    public Prompt prompt() {
        return new StackedPrompt("int") {
            @Override
            public String getRange(final AnnotationProvider provider) {
                return StackedPrompt.getRangeInt(provider);
            }
        };
    }

}
