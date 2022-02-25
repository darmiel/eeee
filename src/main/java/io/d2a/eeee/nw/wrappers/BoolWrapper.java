package io.d2a.eeee.nw.wrappers;

import io.d2a.eeee.nw.PromptWrapper;
import io.d2a.eeee.nw.WrapContext;
import io.d2a.eeee.nw.display.Components;
import io.d2a.eeee.nw.display.PromptDisplay;
import io.d2a.eeee.nw.display.StackPromptDisplay;
import io.d2a.eeee.nw.exception.WrapException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BoolWrapper implements PromptWrapper<Boolean> {

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
    public Boolean wrap(final String input, final WrapContext ctx) throws Exception {
        if (input.trim().isEmpty()) {
            throw WrapException.INPUT_EMPTY;
        }
        return YES.contains(input.trim().toLowerCase());
    }

    @Override
    // [Boolean] [1.0-12.4] Hello [1.0] >
    public PromptDisplay prompt(final WrapContext ctx) {
        return new StackPromptDisplay(
            Components.TYPE,
            Components.PROMPT,
            Components.DEFAULT
        );
    }

}
