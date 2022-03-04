package io.d2a.eeee.table;

import static io.d2a.eeee.converter.StringConverter.repeat;

import io.d2a.eeee.util.AnsiColor;

public class Table {

    protected final Row header;
    protected final int[] maxWidths;

    protected Row[] data;
    protected boolean color;

    Table(final Row header, final Row[] data, final boolean color) {
        this.header = header;
        this.data = data;
        this.color = color;

        // find max widths
        final Cell[] headers = header.getCells();
        this.maxWidths = new int[headers.length];
        for (int i = 0; i < headers.length; i++) {
            this.maxWidths[i] = headers[i].getLength();
            for (final Row dataRow : this.data) {
                final Cell cell = dataRow.getCells()[i];
                if (this.maxWidths[i] < cell.getLength()) {
                    this.maxWidths[i] = cell.getLength();
                }
            }
        }
    }

    public int[] getMaxWidths() {
        return maxWidths;
    }

    protected String drawWithCorners(
        final String tl,
        final String tr,
        final String bl,
        final String br
    ) {
        final StringBuilder bob = new StringBuilder();

        final String[] headerStrings = new String[this.maxWidths.length];
        for (int i = 0; i < this.maxWidths.length; i++) {
            headerStrings[i] = repeat("─", this.maxWidths[i] + 2);
        }

        final String headerColor = this.color
            ? AnsiColor.BLUE_BACKGROUND
            : "";

        bob.append(tl).append(String.join("┬", headerStrings)).append(tr).append('\n');

        bob.append("│ ")
            .append(this.header.toString(headerColor, this.maxWidths))
            .append(" │")
            .append('\n');

        bob.append("├").append(String.join("┼", headerStrings)).append("┤").append('\n');

        for (final Row row : this.data) {
            bob.append("│ ")
                .append(row.toString("", maxWidths))
                .append(" │")
                .append('\n');
        }

        bob.append(bl).append(String.join("┴", headerStrings)).append(br);
        return bob.toString();
    }

    @Override
    public String toString() {
        return this.drawWithCorners("┌", "┐", "└", "┘");
    }

}