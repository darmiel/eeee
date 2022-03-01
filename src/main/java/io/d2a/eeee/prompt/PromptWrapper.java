package io.d2a.eeee.prompt;

import io.d2a.eeee.prompt.display.PromptDisplay;

public interface PromptWrapper<T> extends Wrapper<T> {

    T wrap (final String input, final WrapContext ctx) throws Exception;

    PromptDisplay prompt(final WrapContext ctx);

}
