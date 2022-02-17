package example;

import io.d2a.eeee.Starter;
import io.d2a.eeee.annotations.Entrypoint;
import io.d2a.eeee.annotations.generate.Generate;
import io.d2a.eeee.annotations.generate.Use;
import io.d2a.eeee.annotations.parameters.number.Max;
import io.d2a.eeee.annotations.parameters.number.Min;
import io.d2a.eeee.generate.placeholder.RandomFactory;
import io.d2a.eeee.generate.placeholder.generators.RandomNameGenerator;
import java.util.stream.Stream;

public class RandomClasses {

    public static void main(String[] args) throws Exception {
        Starter.start(RandomClasses.class, args);
    }

    public static class Person {
        private final String name;
        private final int age;
        private final int a;
        private final int b;

        @Generate @Min(20)
        public Person(
            @Use(RandomNameGenerator.class) final String name,
            @Min(1) final int age,
            final int a,
            final int b
        ) {
            this.name = name;
            this.age = age;
            this.a = a;
            this.b = b;
        }

        @Override
        public String toString() {
            return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", a=" + a +
                ", b=" + b +
                '}';
        }
    }

    @Entrypoint
    public void run() throws Exception {
        final Person[] persons = new Person[5];
        RandomFactory.fillArrayRandom(persons, Person.class);
        Stream.of(persons).forEach(System.out::println);
    }

}
