package io.d2a.eeee.prompt.exception;

public class WrapException extends Exception {

    // predefined
    public static final WrapException INPUT_EMPTY = new WrapException("input was empty", Action.RETRY);


    public enum Action {
        RETRY,
        EXIT
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
