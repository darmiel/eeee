package io.d2a.eeee.table;

public class Cell {

    private final TextAlign align;
    private final String value;

    public Cell(final TextAlign align, final String value) {
        this.align = align;
        this.value = value;
    }

    public String toString(final int width) {
        return this.align.format(this.value, width);
    }

    public int getLength() {
        return this.value.length();
    }

}
