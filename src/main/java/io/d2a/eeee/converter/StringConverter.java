package io.d2a.eeee.converter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StringConverter {

    public static final char[] POWERS =
        "⁰¹²³⁴⁵⁶⁷⁸⁹⁻".toCharArray();

    /**
     * Converts an integer to the power representation:
     * <pre>
     *     12345 -> ¹²³⁴⁵
     * </pre>
     *
     * @param exp exponent
     * @return {exp} in power representation
     */
    public static String toPowString(int exp) {
        if (exp == 0) {
            return String.valueOf(POWERS[0]);
        }
        if (exp < 0) {
            return POWERS[10] + toPowString(exp * -1);
        }
        final StringBuilder bob = new StringBuilder();
        while (exp > 0) {
            bob.insert(0, POWERS[exp % 10]);
            exp /= 10;
        }
        return bob.toString();
    }

    /**
     * Repeats a {sequence} {n} times
     *
     * <pre>
     *     repeat("-", 3) -> ---
     * </pre>
     *
     * @param sequence Content which should be repeated
     * @param n        Number of repetitions
     * @return {sequence} * {n}
     */
    public static String repeat(final CharSequence sequence, final int n) {
        final StringBuilder bob = new StringBuilder();
        for (int i = 0; i < n; i++) {
            bob.append(sequence);
        }
        return bob.toString();
    }

    /**
     * @param clazz Class to return all superclasses from
     * @return a list with all superclasses from {clazz}
     */
    private static List<Class<?>> getSuperclasses(final Class<?> clazz) {
        if (clazz == null || clazz == Object.class) {
            return Collections.emptyList();
        }
        final List<Class<?>> superclasses = new ArrayList<>();
        superclasses.add(clazz);
        superclasses.addAll(getSuperclasses(clazz.getSuperclass()));
        return superclasses;
    }

    /**
     * Example:
     *
     * <pre>
     * class io.d2a.eeee.converter.A
     *  └ class io.d2a.eeee.converter.B
     *     └ class io.d2a.eeee.converter.C
     *        └ class io.d2a.eeee.converter.D
     *           └ class io.d2a.eeee.converter.E
     *              └ class io.d2a.eeee.converter.G
     * </pre>
     *
     * @param clazz Class to print super classes
     * @return Formatted string
     */
    private static String getSuperTree(final Class<?> clazz) {
        final List<Class<?>> superclasses = getSuperclasses(clazz);
        final StringBuilder bob = new StringBuilder();

        for (int i = 0; i < superclasses.size(); i++) {
            final Class<?> parentReverse = superclasses.get(
                superclasses.size() - i - 1
            );

            if (i != 0) {
                // append depth level
                bob.append(repeat(" ", i * 3 - 2));
                bob.append("└ ");
            }
            bob.append(parentReverse.toString()).append('\n');
        }
        return bob.toString();
    }

    public static String formatTypes(final Class<?>[] types, boolean sh) {
        final StringBuilder bob = new StringBuilder();
        for (final Class<?> type : types) {
            if (bob.length() > 0) {
                bob.append(", ");
            }
            if (sh) {
                bob.append(type.getSimpleName().charAt(0));
            } else {
                bob.append(type.getSimpleName());
            }
        }
        return bob.toString();
    }

}
