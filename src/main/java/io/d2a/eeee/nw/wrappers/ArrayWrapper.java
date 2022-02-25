package io.d2a.eeee.nw.wrappers;

import io.d2a.eeee.annotation.Annotations;
import io.d2a.eeee.annotation.annotations.Range;
import io.d2a.eeee.annotation.annotations.Split;
import io.d2a.eeee.nw.Validate;
import io.d2a.eeee.nw.ValidateContext;
import io.d2a.eeee.nw.WrapContext;
import io.d2a.eeee.nw.Wrapper;
import io.d2a.eeee.nw.Wrappers;
import io.d2a.eeee.nw.exception.ValidateException;
import io.d2a.eeee.nw.display.Components;
import io.d2a.eeee.nw.display.StackPromptDisplay;
import io.d2a.eeee.nw.display.PromptDisplay;
import java.lang.reflect.Array;

public class ArrayWrapper implements Wrapper<Object>, Validate<Object> {

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

        final String[] components = input.split(this.getSplitAt(ctx));

        // create new instance of array
        final Object array = Array.newInstance(componentType, components.length);
        for (int i = 0; i < components.length; i++) {
            final String component = components[i].trim();
            final Object val = componentWrapper.wrap(component, ctx);
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
