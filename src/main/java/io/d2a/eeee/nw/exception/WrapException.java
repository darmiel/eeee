package io.d2a.eeee.nw.exception;

public class WrapException extends Exception {

    public enum Action {
        RETRY,
        EXIT;
    }

    private final Action action;

    public WrapException(final String message, final Action action) {
        super(message);
        this.action = action;
    }

    public Action getAction() {
        return action;
    }

}
