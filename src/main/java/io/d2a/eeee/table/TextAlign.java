package io.d2a.eeee.table;

import static io.d2a.eeee.converter.StringConverter.repeat;

public enum TextAlign {

    LEFT,
    RIGHT,
    CENTER;

    public String format(final String input, final int width) {
        final int pad = width - input.length();

        switch (this) {
            case LEFT:
                return input + repeat(" ", pad);
            case RIGHT:
                return repeat(" ", pad) + input;
            case CENTER:
                final String a = repeat(" ", pad / 2);
                return a + input + repeat(" ", width - input.length() - a.length());
            default:
                return "#" + input + "#";

        }
    }

}
