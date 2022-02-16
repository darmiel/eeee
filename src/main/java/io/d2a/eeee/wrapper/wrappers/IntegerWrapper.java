package io.d2a.eeee.wrapper.wrappers;

import io.d2a.eeee.annotations.Annotations;
import io.d2a.eeee.annotations.parameters.number.Max;
import io.d2a.eeee.annotations.parameters.number.Min;
import io.d2a.eeee.prompt.NamedRangePrompt;
import io.d2a.eeee.prompt.Prompt;
import io.d2a.eeee.wrapper.DefaultRangeWrapper;

public class IntegerWrapper extends DefaultRangeWrapper<Integer> {

    @Override
    public Integer wrapValue(final String def) {
        if (def.length() == 0) {
            return 0;
        }
        return Integer.parseInt(def);
    }

    @Override
    public boolean testRange(final Integer integer, final Double min, final Double max) {
        return Annotations.testRange(min, max, integer);
    }

    @Override
    public Prompt prompt() {
        return NamedRangePrompt.of("int")
            .replace("range", p -> {
                final Double min = Annotations.get(Min.class, p);
                final Double max = Annotations.get(Max.class, p);
                return Annotations.range(min, max, true);
            });
    }

}
