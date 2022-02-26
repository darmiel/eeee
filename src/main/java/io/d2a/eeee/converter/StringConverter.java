package io.d2a.eeee.converter;

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

}
