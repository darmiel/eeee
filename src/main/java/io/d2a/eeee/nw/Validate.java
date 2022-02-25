package io.d2a.eeee.nw;

import io.d2a.eeee.nw.exception.ValidateException;

public interface Validate<T> {

    void check(final T input, final ValidateContext ctx) throws ValidateException;

}
