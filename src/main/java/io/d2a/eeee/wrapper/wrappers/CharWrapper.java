package io.d2a.eeee.wrapper.wrappers;

import io.d2a.eeee.annotation.Annotations;
import io.d2a.eeee.annotation.provider.AnnotationProvider;
import io.d2a.eeee.prompt.Prompt;
import io.d2a.eeee.prompt.StackedPrompt;
import io.d2a.eeee.wrapper.DefaultRangeWrapper;

public class CharWrapper extends DefaultRangeWrapper<Character> {

    @Override
    public boolean testRange(
        final Character character,
        final double min,
        final double max,
        final double step
    ) {
        return Annotations.testRange(min, max, step, (int) character);
    }

    @Override
    public Character wrapValue(final String def, final AnnotationProvider provider) {
        return def.charAt(0);
    }

    @Override
    public Prompt prompt() {
        return new StackedPrompt("char");
    }

}
