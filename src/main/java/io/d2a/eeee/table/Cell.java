package io.d2a.eeee.table;

public class Cell {

    private final TextAlign align;
    private final String value;

    public Cell(final TextAlign align, final String value) {
        this.align = align;
        this.value = value;
    }

    public String toString(final String color, final int width) {
        return this.align.format(color, this.value, width);
    }

    public int getLength() {
        return this.value.length();
    }

    public static Cell[] from(final TextAlign align, final String ... str) {
        final Cell[] res = new Cell[str.length];
        for (int i = 0; i < str.length; i++) {
            res[i] = new Cell(align, str[i]);
        }
        return res;
    }

}
