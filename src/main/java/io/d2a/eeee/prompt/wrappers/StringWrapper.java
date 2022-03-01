package io.d2a.eeee.prompt.wrappers;

import io.d2a.eeee.annotation.Annotations;
import io.d2a.eeee.annotation.annotations.common.Range;
import io.d2a.eeee.annotation.annotations.common.Transform;
import io.d2a.eeee.annotation.annotations.common.Transform.Type;
import io.d2a.eeee.annotation.annotations.prompt.Pattern;
import io.d2a.eeee.prompt.PromptWrapper;
import io.d2a.eeee.prompt.Validate;
import io.d2a.eeee.prompt.ValidateContext;
import io.d2a.eeee.prompt.WrapContext;
import io.d2a.eeee.prompt.display.Components;
import io.d2a.eeee.prompt.display.PromptDisplay;
import io.d2a.eeee.prompt.display.StackPromptDisplay;
import io.d2a.eeee.prompt.exception.ValidateException;
import io.d2a.eeee.prompt.exception.WrapException.Action;

public class StringWrapper implements PromptWrapper<String>, Validate<String> {

    @Override
    public String wrap(final String input, final WrapContext ctx) {
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
            c -> {
                // pattern
                final Pattern pattern = c.getProvider().get(Pattern.class);
                if (pattern == null) {
                    return null;
                }
                return String.format("/%s/", pattern.value());
            },
            Components.RANGE_INT,
            Components.PROMPT,
            Components.DEFAULT
        );
    }

    @Override
    public void check(final String input, final ValidateContext ctx) throws ValidateException {
        // check range
        final double[] range = Annotations.getRange(ctx.a(Range.class));
        Annotations.checkRange(input.length(), range);

        // check pattern
        final Pattern pattern = ctx.a(Pattern.class);
        if (pattern != null && !input.matches(pattern.value())) {
            throw new ValidateException(
                "string doesn't match pattern: " + pattern.value(),
                Action.RETRY
            );
        }
    }

}
