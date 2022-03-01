package io.d2a.eeee.prompt;

import io.d2a.eeee.prompt.exception.ValidateException;

public interface Validate<T> {

    void check(final T input, final ValidateContext ctx) throws ValidateException;

}
