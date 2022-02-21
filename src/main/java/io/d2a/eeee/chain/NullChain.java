package io.d2a.eeee.chain;

import java.util.function.Supplier;

/**
 * This class emulates Optional Chaining (?.) for Java.
 *
 * Example (JavaScript):
 * <pre>
 *     const val = obj?.owner?.name;
 * </pre>
 *
 * Example (NullChain):
 * <pre>
 *     final String name = NullChain.of(obj).then(Obj::owner).then(Person::name).orNull();
 * </pre>
 *
 * If the value is null in a step, null is returned.
 * So you don't have to worry about whether an NPE could be thrown.
 *
 * Psychopaths can also use the yolo() method, for a shorter syntax, but at a cost...
 * <pre>
 *     final String name = NullChain.yolo(() -> obj.owner.name);
 * </pre>
 */
public class NullChain<T> {

    private final T value;

    private NullChain(final T t) {
        this.value = t;
    }

    ///

    public static <T> NullChain<T> of(final T t) {
        return new NullChain<>(t);
    }

    public static <T> NullChain<T> nullChain() {
        return of(null);
    }

    ///

    public <A> NullChain<A> then(final Then<T, A> then) {
        if (this.value == null) {
            return NullChain.nullChain();
        }
        return NullChain.of(then.accept(this.value));
    }

    /**
     * @return value or null
     */
    public T get() {
        return this.value;
    }

    /**
     * @param def default value of value is null
     * @return value or {def}
     */
    public T orElse(final T def) {
        return this.get() != null ? this.get() : def;
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
