package io.d2a.eeee.prompt.wrappers;

import io.d2a.eeee.annotation.Annotations;
import io.d2a.eeee.annotation.annotations.common.Range;
import io.d2a.eeee.prompt.PromptWrapper;
import io.d2a.eeee.prompt.Validate;
import io.d2a.eeee.prompt.ValidateContext;
import io.d2a.eeee.prompt.WrapContext;
import io.d2a.eeee.prompt.display.Components;
import io.d2a.eeee.prompt.display.PromptDisplay;
import io.d2a.eeee.prompt.display.StackPromptDisplay;
import io.d2a.eeee.prompt.exception.ValidateException;
import io.d2a.eeee.prompt.exception.WrapException;

public class DoubleWrapper implements PromptWrapper<Double>, Validate<Double> {

    @Override
    public Double wrap(final String input, final WrapContext ctx) throws Exception {
        if (input.trim().isEmpty()) {
            throw WrapException.INPUT_EMPTY;
        }
        return Double.parseDouble(input);
    }

    @Override
    public void check(final Double input, final ValidateContext ctx) throws ValidateException {
        final double[] range = Annotations.getRange(ctx.a(Range.class));
        Annotations.checkRange(input, range);
    }

    @Override
    // [double] [1.0-12.4] Hello [1.0] >
    public PromptDisplay prompt(final WrapContext ctx) {
        return new StackPromptDisplay(
            Components.TYPE,
            Components.RANGE_DOUBLE,
            Components.PROMPT,
            Components.DEFAULT
        );
    }

}
