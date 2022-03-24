package io.d2a.eeee.util;

import io.d2a.eeee.chain.Then;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Transformers {

    public static <S, T> T[] array(
        final S[] original,
        final Class<T> clazz,
        final Then<S, T> wrapper
    ) {
        return array(original, clazz, wrapper, false);
    }

    public static <S, T> T[] array(
        final S[] original,
        final Class<T> clazz,
        final Then<S, T> wrapper,
        final Consumer<S> onNull
    ) {
        return array(original, clazz, wrapper, false, onNull);
    }

    public static <S, T> T[] array(
        final S[] original,
        final Class<T> clazz,
        final Then<S, T> wrapper,
        final boolean includeNulls
    ) {
        return array(original, clazz, wrapper, includeNulls, null);
    }

    @SuppressWarnings("unchecked")
    public static <S, T> T[] array(
        final S[] original,
        final Class<T> clazz,
        final Then<S, T> wrapper,
        final boolean includeNulls,
        final Consumer<S> onNull
    ) {
        final List<T> res = new ArrayList<>();
        for (final S s : original) {
            final T target = wrapper.accept(s);
            if (target == null) {
                if (onNull != null) {
                    onNull.accept(s);
                }
                if (!includeNulls) {
                    continue;
                }
            }
            res.add(target);
        }
        final T[] resArray = (T[]) Array.newInstance(clazz, res.size());
        for (int i = 0; i < res.size(); i++) {
            resArray[i] = res.get(i);
        }
        return resArray;
    }

}
