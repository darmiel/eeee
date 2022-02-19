package io.d2a.eeee.wrapper.wrappers;

import io.d2a.eeee.annotation.Annotations;
import io.d2a.eeee.annotation.annotations.Transform;
import io.d2a.eeee.annotation.annotations.Transform.Type;
import io.d2a.eeee.annotation.provider.AnnotationProvider;
import io.d2a.eeee.prompt.Prompt;
import io.d2a.eeee.prompt.StackedPrompt;
import io.d2a.eeee.wrapper.DefaultRangeWrapper;

public class StringWrapper extends DefaultRangeWrapper<String> {

    @Override
    public String wrapValue(String def, final AnnotationProvider provider) {
        // transform string
        final Transform transform = provider.get(Transform.class);
        if (transform != null) {
            for (final Type type : transform.value()) {
                def = type.transform(def);
            }
        }
        return def;
    }

    @Override
    public boolean testRange(final String s, final double min, final double max,
        final double step) {
        return Annotations.testRange(min, max, step, s.length());
    }

    @Override
    public Prompt prompt() {
        return new StackedPrompt("string") {
            @Override
            public String getRange(final AnnotationProvider provider) {
                return StackedPrompt.getRangeInt(provider);
            }
        };
    }

}
