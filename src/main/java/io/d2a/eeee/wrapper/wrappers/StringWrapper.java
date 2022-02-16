package io.d2a.eeee.wrapper.wrappers;

import io.d2a.eeee.annotations.Annotations;
import io.d2a.eeee.prompt.NamedRangePrompt;
import io.d2a.eeee.prompt.Prompt;
import io.d2a.eeee.wrapper.DefaultRangeWrapper;

public class StringWrapper extends DefaultRangeWrapper<String> {

    @Override
    public String wrapValue(final String def) {
        return def;
    }

    @Override
    public boolean testRange(final String s, final Double min, final Double max) {
        return Annotations.testRange(min, max, s.length());
    }

    @Override
    public Prompt prompt() {
        return NamedRangePrompt.of("string");
    }
    
}
