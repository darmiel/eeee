package io.d2a.eeee.wrapper.wrappers;

import io.d2a.eeee.annotation.provider.AnnotationProvider;
import io.d2a.eeee.prompt.Prompt;
import io.d2a.eeee.prompt.StackedPrompt;
import io.d2a.eeee.wrapper.DefaultRangeWrapper;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BooleanWrapper extends DefaultRangeWrapper<Boolean> {

    private static final Set<String> YES = new HashSet<>(Arrays.asList(
        "true",
        "yes",
        "1",
        "ja",
        "jo",
        "y",
        "j"
    ));

    @Override
    public Boolean wrapValue(final String def, final AnnotationProvider provider) {
        return YES.contains(def.toLowerCase().trim());
    }

    @Override
    public Prompt prompt() {
        return new StackedPrompt("bool") {
            @Override
            public String getRange(final AnnotationProvider provider) {
                return null;
            }
        };
    }

}
