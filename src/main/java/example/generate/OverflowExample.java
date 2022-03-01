package example.generate;

import io.d2a.eeee.annotation.annotations.common.Use;
import io.d2a.eeee.annotation.annotations.generate.Depth;
import io.d2a.eeee.annotation.annotations.generate.Generate;
import io.d2a.eeee.generate.random.RandomFactory;
import io.d2a.eeee.generate.random.generators.special.NameGenerator;

public class OverflowExample {


    public static class OverflowingPerson {

        private final String name;
        private final OverflowingPerson next;

        @Generate
        public OverflowingPerson(
            @Use(NameGenerator.class) final String name,
            final OverflowingPerson next // throws StackOverflowException
        ) {
            this.name = name;
            this.next = next;
        }

        @Override
        public String toString() {
            return "OverflowingPerson{" +
                "name='" + name + '\'' +
                ", next=" + next +
                '}';
        }
    }

    public static class Person {

        private final String name;
        private final Person next;

        @Generate
        public Person(
            @Use(NameGenerator.class) final String name,
            @Depth(1) final Person next // throws nothing
        ) {
            this.name = name;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Person{" +
                "name='" + name + '\'' +
                ", next=" + next +
                '}';
        }

    }

    public static void main(String[] args) throws Exception {
        {
            // throws nothing
            final Person person = RandomFactory.generate(Person.class, "");
            System.out.println(person);
        }

        {
            // throws StackOverflowException
            final OverflowingPerson person = RandomFactory.generate(OverflowingPerson.class, "");
            System.out.println(person);
        }
    }

}
