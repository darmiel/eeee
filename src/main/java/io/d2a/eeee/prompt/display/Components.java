package io.d2a.eeee.prompt.display;

import io.d2a.eeee.annotation.Annotations;
import io.d2a.eeee.annotation.annotations.common.Range;
import io.d2a.eeee.annotation.annotations.prompt.Default;
import io.d2a.eeee.chain.Then;

public class Components {

    public static final Component TYPE =
        ctx -> String.format("[%s]", ctx.getType().getSimpleName());

    private static Component getRangeComponent(final Then<Double, String> wrap) {
        return ctx -> {
            final double[] range = Annotations.getRange(ctx.getProvider().get(Range.class));
            if (range.length == 0) {
                return null;
            }
            final StringBuilder bob = new StringBuilder();
            bob.append('[')
                .append(wrap.accept(range[0])).append('-')
                .append(wrap.accept(range[1]));
            if (range[2] != 0) {
                bob.append('%').append(wrap.accept(range[3]));
            }
            return bob.append(']').toString();
        };
    }

    public static final Component RANGE_DOUBLE =
        getRangeComponent(d -> String.format("%.4f", d));

    public static final Component RANGE_INT =
        getRangeComponent(d -> String.valueOf(d.intValue()));

    public static final Component DEFAULT = ctx -> {
        final Default def = ctx.getProvider().get(Default.class);
        if (def == null) {
            return null;
        }
        return '[' + def.value() + ']';
    };

    public static final Component PROMPT = ComponentContext::getDisplay;

}
