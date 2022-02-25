package io.d2a.eeee.nw.wrappers;

import io.d2a.eeee.annotation.Annotations;
import io.d2a.eeee.annotation.annotations.Range;
import io.d2a.eeee.nw.PromptWrapper;
import io.d2a.eeee.nw.Validate;
import io.d2a.eeee.nw.ValidateContext;
import io.d2a.eeee.nw.WrapContext;
import io.d2a.eeee.nw.display.Components;
import io.d2a.eeee.nw.display.PromptDisplay;
import io.d2a.eeee.nw.display.StackPromptDisplay;
import io.d2a.eeee.nw.exception.ValidateException;
import io.d2a.eeee.nw.exception.WrapException;

public class ShortWrapper implements PromptWrapper<Short>, Validate<Short> {

    @Override
    public Short wrap(final String input, final WrapContext ctx) throws Exception {
        if (input.trim().isEmpty()) {
            throw WrapException.INPUT_EMPTY;
        }
        return Short.parseShort(input);
    }

    @Override
    public void check(final Short input, final ValidateContext ctx) throws ValidateException {
        final double[] range = Annotations.getRange(ctx.a(Range.class));
        Annotations.checkRange(input, range);
    }

    @Override
    // [short] [1.0-12.4] Hello [1.0] >
    public PromptDisplay prompt(final WrapContext ctx) {
        return new StackPromptDisplay(
            Components.TYPE,
            Components.RANGE_DOUBLE,
            Components.PROMPT,
            Components.DEFAULT
        );
    }

}
