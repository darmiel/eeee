package io.d2a.eeee.nw.wrappers;

import io.d2a.eeee.annotation.Annotations;
import io.d2a.eeee.annotation.annotations.Range;
import io.d2a.eeee.annotation.annotations.Transform;
import io.d2a.eeee.annotation.annotations.Transform.Type;
import io.d2a.eeee.nw.Validate;
import io.d2a.eeee.nw.ValidateContext;
import io.d2a.eeee.nw.WrapContext;
import io.d2a.eeee.nw.Wrapper;
import io.d2a.eeee.nw.display.Component;
import io.d2a.eeee.nw.display.Components;
import io.d2a.eeee.nw.display.PromptDisplay;
import io.d2a.eeee.nw.display.StackPromptDisplay;
import io.d2a.eeee.nw.exception.ValidateException;

public class StringWrapper implements Wrapper<String>, Validate<String> {

    @Override
    public String wrap(final String input, final WrapContext ctx) throws Exception {
        final Transform transform = ctx.a(Transform.class);

        String result = input;
        if (transform != null) {
            for (final Type type : transform.value()) {
                result = type.transform(result);
            }
        }

        return result;
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

    @Override
    public void check(final String input, final ValidateContext ctx) throws ValidateException {
        final double[] range = Annotations.getRange(ctx.a(Range.class));
        Annotations.checkRange(input.length(), range);
    }

}
