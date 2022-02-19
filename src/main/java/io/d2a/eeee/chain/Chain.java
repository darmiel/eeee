package io.d2a.eeee.chain;

import java.util.function.Supplier;

public class Chain<T> {

    private final T value;

    private Chain(final T t) {
        this.value = t;
    }

    public static <T> Chain<T> nullChain() {
        return new Chain<>(null);
    }

    public static <T> Chain<T> of(final T t) {
        return new Chain<T>(t);
    }

    public <A> Chain<A> then(final Then<T, A> then) {
        if (this.value == null) {
            return Chain.nullChain();
        }
        return Chain.of(then.accept(this.value));
    }

    public T get() {
        return this.value;
    }

    public T orElse(final T def) {
        return this.get() != null ? this.get() : def;
    }

    public T orNull() {
        return this.orElse(null);
    }

    public static <T> T yolo(final Supplier<T> supplier, final T def) {
        final T val = yolo(supplier);
        if (val == null) {
            return def;
        }
        return val;
    }

    /**
     * The method is called "yolo" for a reason okay?
     *
     * @param supplier The value that can throw a NPE
     * @param <T>      Type of value
     * @return Value or null
     */
    public static <T> T yolo(final Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (NullPointerException npex) {
            return null;
        }
    }

}
