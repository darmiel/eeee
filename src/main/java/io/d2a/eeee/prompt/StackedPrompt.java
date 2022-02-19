package io.d2a.eeee.prompt;

import io.d2a.eeee.annotation.Annotations;
import io.d2a.eeee.annotation.annotations.Range;
import io.d2a.eeee.annotation.provider.AnnotationProvider;
import io.d2a.eeee.chain.Then;

public class StackedPrompt implements Prompt {

    private final String type;

    public StackedPrompt(final String type) {
        this.type = type;
    }

    public static String getRangeGeneric(
        final AnnotationProvider provider,
        final Then<Double, String> wrapMin,
        final Then<Double, String> wrapMax,
        final Then<Double, String> wrapStep
    ) {
        final Range range = provider.get(Range.class);
        if (range == null) {
            return null;
        }
        final double min = Annotations.getRangeMin(range.value());
        final double max = Annotations.getRangeMax(range.value());
        final double step = Annotations.getRangeStep(range.value());
        return String.format("[%s-%s#%s]", wrapMin.accept(min), wrapMax.accept(max),
            wrapStep.accept(step));
    }

    public static String getRangeDouble(final AnnotationProvider provider) {
        final Then<Double, String> wrap = b -> String.format("%.1f", b);
        return getRangeGeneric(provider, wrap, wrap, wrap);
    }

    public static String getRangeInt(final AnnotationProvider provider) {
        final Then<Double, String> wrap = b -> String.format("%d", b.intValue());
        return getRangeGeneric(provider, wrap, wrap, wrap);
    }

    public String getRange(final AnnotationProvider provider) {
        return StackedPrompt.getRangeDouble(provider);
    }

    @Override
    public String prompt(final AnnotationProvider provider, final String prompt, final String def) {
        final StringBuilder bob = new StringBuilder("? ");
        bob.append('[').append(this.type).append("] ");

        final String range = this.getRange(provider);
        if (range != null && range.length() > 0) {
            bob.append(range).append(' ');
        }

        bob.append(prompt);

        if (def != null) {
            bob.append(" [").append(def).append("]");
        }

        bob.append(" > ");
        return bob.toString();
    }

}
