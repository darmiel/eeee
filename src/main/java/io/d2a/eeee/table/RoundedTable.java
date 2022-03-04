package io.d2a.eeee.table;

public class RoundedTable extends Table {

    public RoundedTable(final Row header, final Row[] data, final boolean color) {
        super(header, data, color);
    }

    @Override
    public String toString() {
        return this.drawWithCorners("╭", "╮", "╰", "╯");
    }

}
