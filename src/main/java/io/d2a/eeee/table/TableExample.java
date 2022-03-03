package io.d2a.eeee.table;

import static io.d2a.eeee.converter.StringConverter.repeat;

import io.d2a.eeee.annotation.annotations.common.Range;
import io.d2a.eeee.annotation.annotations.common.Use;
import io.d2a.eeee.annotation.annotations.generate.Generate;
import io.d2a.eeee.generate.random.RandomFactory;
import io.d2a.eeee.generate.random.generators.special.NameGenerator;

public class TableExample {

    private static class Person {
        @Column("Name")
        public final String name;

        @Column("Age")
        public final int age;

        @Generate
        public Person(
            @Use(NameGenerator.class) final String name,
            @Range({18, 100}) final int age
        ) {
            this.name = name;
            this.age = age;
        }
    }

    private static <T> void a(final Iterable<T> it) {
        for (final T t : it) {
            System.out.println(t);
        }
    }

    public static void main(String[] args) {
        final int[] maxWidths = new int[] {13, 13};

        final String[] headerStrings = new String[maxWidths.length];
        for (int i = 0; i < maxWidths.length; i++) {
            headerStrings[i] = repeat("─", maxWidths[i] + 2);
        }
        System.out.println("╭" + String.join("┬", headerStrings) + "╮");

        final Row header = new Row(new Cell[] {
            new Cell(TextAlign.CENTER, "First Name"),
            new Cell(TextAlign.CENTER, "Last Name")
        });
        System.out.println("│ " + header.toString(maxWidths) + " │");
        System.out.println("├" + String.join("┼", headerStrings) + "┤");


        final Row[] rows = new Row[] {
            new Row(new Cell[] {
                new Cell(TextAlign.LEFT, "Joe"),
                new Cell(TextAlign.LEFT, "Smith")
            }),
            new Row(new Cell[] {
                new Cell(TextAlign.CENTER, "Joe"),
                new Cell(TextAlign.CENTER, "Smith")
            }),
            new Row(new Cell[] {
                new Cell(TextAlign.RIGHT, "Joe"),
                new Cell(TextAlign.RIGHT, "Smith")
            }),
        };


        for (final Row row : rows) {
            System.out.println("│ " + row.toString(maxWidths) + " │");
        }

        System.out.println("╰" + String.join("┴", headerStrings) + "╯");

        if (true) {
            return;
        }

        // generate some person objects
        final Person[] people = new Person[10];
        RandomFactory.fillUnsafe(people);

        // final Table table = new Table(
        //   Table.headers("Name", "Age"),
        //   Table.ann(people)
        // );
        //   Table.it(people, person -> {
        //     Table.collect(person.name, person.age)
        //   })
        // );
    }

}
