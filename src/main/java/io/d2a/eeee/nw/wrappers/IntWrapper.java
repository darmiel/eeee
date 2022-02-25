package io.d2a.eeee.nw.wrappers;

import io.d2a.eeee.annotation.Annotations;
import io.d2a.eeee.annotation.annotations.Range;
import io.d2a.eeee.nw.Validate;
import io.d2a.eeee.nw.ValidateContext;
import io.d2a.eeee.nw.WrapContext;
import io.d2a.eeee.nw.Wrapper;
import io.d2a.eeee.nw.exception.ValidateException;
import io.d2a.eeee.nw.exception.WrapException;
import io.d2a.eeee.nw.exception.WrapException.Action;
import io.d2a.eeee.nw.display.Components;
import io.d2a.eeee.nw.display.StackPromptDisplay;
import io.d2a.eeee.nw.display.PromptDisplay;

public class IntWrapper implements Wrapper<Integer>, Validate<Integer> {

    @Override
    public Integer wrap(final String input, final WrapContext ctx) throws Exception {
        if (input.trim().length() == 0) {
            throw new WrapException("input was empty", Action.RETRY);
        }
        return Integer.parseInt(input);
    }

    @Override
    public void check(final Integer input, final ValidateContext ctx) throws ValidateException {
        final double[] range = Annotations.getRange(ctx.a(Range.class));
        Annotations.checkRange(input.doubleValue(), range);
    }

    @Override
    public PromptDisplay prompt(final WrapContext ctx) {
        return new StackPromptDisplay(
            Components.TYPE,
            Components.RANGE_INT,
            Components.PROMPT,
            Components.DEFAULT
        );
    }

}
