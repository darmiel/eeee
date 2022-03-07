package io.d2a.eeee.table;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class TableBuilder {

    private final String[] headers;

    private boolean color;
    private TableStyle style = TableStyle.SHARP;
    private Row[] data;

    private TableBuilder(final String... headers) {
        this.headers = headers;
    }

    ///

    public TableBuilder data(final Row[] rows) {
        this.data = rows;
        return this;
    }

    public TableBuilder style(final TableStyle style) {
        this.style = style;
        return this;
    }

    public TableBuilder color(final boolean color) {
        this.color = color;
        return this;
    }

    public static String[] array(final String... val) {
        return val;
    }

    public <T> TableBuilder loads(final Collection<T> c, final Stringer<T> stringer) {
        final Collection<T> collection = c.stream()
            .filter(Objects::nonNull)
            .collect(Collectors.toCollection(ArrayList::new));

        if (collection.size() == 0) {
            return this;
        }

        final int offset;
        if (this.data == null) {
            offset = 0;
            this.data = new Row[collection.size()];
        } else {
            offset = this.data.length;
            final Row[] newData = new Row[this.data.length + collection.size()];
            System.arraycopy(this.data, 0, newData, 0, this.data.length);
            this.data = newData;
        }

        int index = 0;
        for (final T obj : collection) {
            final Cell[] cells = new Cell[this.headers.length];
            final String[] strings = stringer.stringify(obj);
            for (int i = 0; i < strings.length; i++) {
                cells[i] = new Cell(TextAlign.LEFT, strings[i]);
            }
            this.data[offset + index++] = new Row(cells);
        }
        return this;
    }

    @SafeVarargs
    public final <T> TableBuilder loads(final Class<T> clazz, final T... values)
        throws IllegalArgumentException, IllegalAccessException {

        // find fields
        final Map<String, Field> fields = new HashMap<>();
        for (final Field field : clazz.getDeclaredFields()) {
            if (!field.isAnnotationPresent(Column.class)) {
                continue;
            }
            final Column column = field.getAnnotation(Column.class);
            field.setAccessible(true);
            fields.put(column.value(), field);
        }

        final String[] r = new String[0];
        return this.loads(Arrays.asList(values), elem -> {
            if (elem == null) {
                return null;
            }
            final List<String> v = new ArrayList<>();
            for (final String header : this.headers) {
                if (!fields.containsKey(header)) {
                    throw new IllegalArgumentException("unknown column: " + header);
                }
                final Object value;
                try {
                    value = fields.get(header).get(elem);
                    final String str = value != null ? value.toString() : "n/a";
                    v.add(str);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            return v.toArray(r);
        });
    }

    ///

    public Table build() {
        final Row headers = new Row(Cell.from(TextAlign.CENTER, this.headers));
        if (this.style == TableStyle.ROUND) {
            return new RoundedTable(headers, this.data, this.color);
        }
        return new Table(headers, this.data, this.color);
    }

    ///

    public static TableBuilder builder(final String... headers) {
        return new TableBuilder(headers);
    }

    public static <T> TableBuilder from(
        final T[] array,
        final Class<T> clazz
    ) throws IllegalAccessException {
        final String[] headers = clazz.isAnnotationPresent(HeaderOrder.class)
            ? clazz.getAnnotation(HeaderOrder.class).value()
            : getHeadersFromClass(clazz);
        return builder(headers)
            .loads(clazz, array);
    }

    public static <T> TableBuilder fromUnsafe(
        final T[] array,
        final Class<T> clazz
    ) {
        try {
            return from(array, clazz);
        } catch (IllegalAccessException e) {
            return null;
        }
    }

    /// Helper

    private static String[] getHeadersFromClass(Class<?> clazz) {
        final Set<String> h = new HashSet<>();
        for (final Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                h.add(field.getAnnotation(Column.class).value());
            }
        }
        return h.toArray(new String[0]);
    }

}
