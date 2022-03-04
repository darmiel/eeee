package example.table;

import io.d2a.eeee.annotation.annotations.common.Range;
import io.d2a.eeee.annotation.annotations.common.Use;
import io.d2a.eeee.annotation.annotations.generate.Generate;
import io.d2a.eeee.generate.random.RandomFactory;
import io.d2a.eeee.generate.random.generators.special.NameGenerator;
import io.d2a.eeee.table.Column;
import io.d2a.eeee.table.HeaderOrder;
import io.d2a.eeee.table.Table;
import io.d2a.eeee.table.TableBuilder;
import io.d2a.eeee.table.TableStyle;
import java.util.Arrays;

public class TableExample {

    @HeaderOrder({"First Name", "Age"})
    public static class Person {

        @Column("First Name")
        public final String first;

        @Column("Age")
        public final int age;


        @Generate
        public Person(
            @Use(NameGenerator.class) final String first,
            @Range({18, 100}) final int age
        ) {
            this.first = first;
            this.age = age;
        }

    }

    public static void main(String[] args) throws Exception {
        // generate some person objects
        for (int i = 0; i < 4; i++) {
            final Person[] people = new Person[10];
            RandomFactory.fillUnsafe(people);

            final Table x = TableBuilder.from(people, Person.class)
                .color((i & 1) == 0)
                .style((i & 1) == 0 ? TableStyle.ROUND : TableStyle.SHARP)
                .build();

            System.out.println(x);
            System.out.println(Arrays.toString(x.getMaxWidths()));
            System.out.println();
        }
    }

}
