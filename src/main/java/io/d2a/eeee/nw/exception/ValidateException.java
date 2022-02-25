package io.d2a.eeee.nw.exception;

import io.d2a.eeee.nw.exception.WrapException.Action;

public class ValidateException extends Exception {

    private final Action action;

    public ValidateException(final String message, final Action action) {
        super(message);
        this.action = action;
    }

    public Action getAction() {
        return action;
    }

}
