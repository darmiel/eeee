package io.d2a.eeee.table;


public class Table {

    private final Row[] headers;
    private final Row[] data;
    private final int[] maxWidths;

    public Table(final Row[] headers, final Row[] data) {
        this.headers = headers;
        this.data = data;

        // find max widths
        this.maxWidths = new int[this.headers.length];
        for (int i = 0; i < headers.length; i++) {
            final Cell[] cells = headers[i].getCells();

            for (int j = 0; j < cells.length; j++) {
                final int len = cells[i].getLength();
                if (len > this.maxWidths[i]) {
                    this.maxWidths[i] = len;
                }
            }
        }
    }



}