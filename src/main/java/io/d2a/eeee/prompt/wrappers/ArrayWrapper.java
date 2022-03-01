package io.d2a.eeee.prompt.wrappers;

import io.d2a.eeee.annotation.Annotations;
import io.d2a.eeee.annotation.annotations.common.Range;
import io.d2a.eeee.annotation.annotations.prompt.Split;
import io.d2a.eeee.prompt.PromptWrapper;
import io.d2a.eeee.prompt.Validate;
import io.d2a.eeee.prompt.ValidateContext;
import io.d2a.eeee.prompt.WrapContext;
import io.d2a.eeee.prompt.Wrapper;
import io.d2a.eeee.prompt.Wrappers;
import io.d2a.eeee.prompt.display.Components;
import io.d2a.eeee.prompt.display.PromptDisplay;
import io.d2a.eeee.prompt.display.StackPromptDisplay;
import io.d2a.eeee.prompt.exception.ValidateException;
import java.lang.reflect.Array;

public class ArrayWrapper implements PromptWrapper<Object>, Validate<Object> {

    private String getSplitAt(final WrapContext ctx) {
        // get split separator
        final Split splitAnnotation = ctx.a(Split.class);
        final String splitAt;
        if (splitAnnotation != null) {
            splitAt = splitAnnotation.value();
        } else {
            splitAt = ",";
        }
        return splitAt;
    }

    @Override
    public Object wrap(final String input, final WrapContext ctx) throws Exception {
        final Class<?> componentType = ctx.getType().getComponentType();
        final Wrapper<?> componentWrapper = Wrappers.findWrapper(componentType);

        // currently, only supports PromptWrapper
        if (!(componentWrapper instanceof PromptWrapper)) {
            throw new IllegalArgumentException("invalid wrapper type for array type");
        }
        final PromptWrapper<?> promptWrapper = (PromptWrapper<?>) componentWrapper;

        final String[] components = input.split(this.getSplitAt(ctx));

        // create new instance of array
        final Object array = Array.newInstance(componentType, components.length);
        for (int i = 0; i < components.length; i++) {
            final String component = components[i].trim();
            final Object val = promptWrapper.wrap(component, ctx);
            Array.set(array, i, val);
        }

        return array;
    }

    @Override
    public PromptDisplay prompt(final WrapContext ctx) {
        return new StackPromptDisplay(
            c -> String.format("[arr<%s>]", ctx.getType().getComponentType().getSimpleName()),
            c -> String.format("/%s/", this.getSplitAt(ctx)),
            Components.RANGE_INT,
            Components.PROMPT,
            Components.DEFAULT
        );
    }

    @Override
    public void check(final Object input, final ValidateContext ctx) throws ValidateException {
        final double[] range = Annotations.getRange(ctx.a(Range.class));
        Annotations.checkRange(Array.getLength(input), range);
    }

}
