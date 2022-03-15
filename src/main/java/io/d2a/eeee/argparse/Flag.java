package io.d2a.eeee.argparse;

public abstract class Flag<T> {

    protected final String key;
    private T wrappedValue;

    private boolean updated = false;

    public Flag(final String key) {
        this.key = key;

        // add current flag to flag set
        Flags.FLAGS.add(this);
    }

    public void updateValue(final String value) throws Exception {
        this.wrappedValue = this.wrap(value);
        this.updated = true;
    }

    // abstracts

    public abstract T wrap(final String input) throws Exception;

    // getters

    public T get() {
        return this.wrappedValue;
    }

    public T get(final T def) {
        final T obj = this.get();
        if (obj == null) {
            return def;
        }
        return obj;
    }

    public boolean isUpdated() {
        return updated;
    }

}
