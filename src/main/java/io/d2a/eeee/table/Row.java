package io.d2a.eeee.table;

public class Row {

    private final Cell[] cells;

    public Row(final Cell[] cells) {
        this.cells = cells;
    }

    public String toString(final String color, final int ... maxWidths) {
        final String[] strings = new String[this.cells.length];
        for (int i = 0; i < this.cells.length; i++) {
            strings[i] = this.cells[i].toString(color, maxWidths[i]);
        }
        return String.join(" │ ", strings);
    }

    public int getSize() {
        return this.cells.length;
    }

    public Cell[] getCells() {
        return cells;
    }

}
