package io.d2a.eeee.table;

import static io.d2a.eeee.converter.StringConverter.repeat;

import io.d2a.eeee.util.AnsiColor;

public enum TextAlign {

    LEFT,
    RIGHT,
    CENTER;

    public String format(final String color, final String input, final int width) {
        final int pad = width - input.length();

        final String val;
        switch (this) {
            case LEFT:
                val = input + repeat(" ", pad);
                break;
            case RIGHT:
                val = repeat(" ", pad) + input;
                break;
            case CENTER:
                final String a = repeat(" ", pad / 2);
                val = a + input + repeat(" ", width - input.length() - a.length());
                break;
            default:
                val = "#" + input + "#";
                break;
        }

        if (!color.isEmpty()) {
            return color + val + AnsiColor.RESET;
        }
        return val;
    }

}
