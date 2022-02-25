package io.d2a.eeee.nw;

import io.d2a.eeee.nw.display.PromptDisplay;

public interface Wrapper<T> {

    T wrap (final String input, final WrapContext ctx) throws Exception;

    PromptDisplay prompt(final WrapContext ctx);

}
