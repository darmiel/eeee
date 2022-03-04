package io.d2a.eeee.table;

import io.d2a.eeee.annotation.annotations.common.Range;
import io.d2a.eeee.annotation.annotations.common.Use;
import io.d2a.eeee.annotation.annotations.generate.Generate;
import io.d2a.eeee.generate.random.RandomFactory;
import io.d2a.eeee.generate.random.generators.special.NameGenerator;
import java.util.Arrays;

public class TableExample {

    @HeaderOrder({"Last Name", "First Name", "Age"})
    public static class Person {

        @Column("First Name")
        public final String first;

        @Column("Last Name")
        public final String last;

        @Column("Age")
        public final int age;


        @Generate
        public Person(
            @Use(NameGenerator.class) final String first,
            @Range({10, 20}) String last,
            @Range({18, 100}) final int age
        ) {
            this.first = first;
            this.last = last;
            this.age = age;
        }

    }

    /*
╭──────────────────────┬──────────────────────┬──────────────────────╮
│█████ Last Name ██████│█████ First Name █████│████████ Age █████████│
├──────────────────────┼──────────────────────┼──────────────────────┤
│ yxORDZKuvNjcZgiDKi   │ Andrew               │ 33                   │
│ ZtpqwECjUcvF         │ Rebecca              │ 50                   │
│ aHnKgPpiMhzWDgJtGk   │ Jeffrey              │ 21                   │
│ TObgyqkYnyMeaCkXwEd  │ Mark                 │ 59                   │
│ XPZBEQqMwupDBfxrHxNf │ Angela               │ 47                   │
│ bDEEYucqYqfNYz       │ Mark                 │ 25                   │
│ OfDGhvydbcbJpaMqoHUQ │ Kimberly             │ 79                   │
│ TNwDxZrUvQuvzcRY     │ Joshua               │ 50                   │
│ gUVshwIyAWmAwAIF     │ Melissa              │ 31                   │
│ qqnXCfnaHEptWxLC     │ Angela               │ 33                   │
╰──────────────────────┴──────────────────────┴──────────────────────╯
     */

    public static void main(String[] args) throws Exception {
        // generate some person objects
        for (int i = 0; i < 10; i++) {
            final Person[] people = new Person[10];
            RandomFactory.fillUnsafe(people);

            final Table x = TableBuilder.builder("Last Name", "First Name", "Age")
                .loads(people)
                .color((i & 1) == 0)
                .style((i & 1) == 0 ? TableStyle.ROUND : TableStyle.SHARP)
                .build();

            System.out.println(x);
            System.out.println(Arrays.toString(x.getMaxWidths()));
            System.out.println();
        }

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
