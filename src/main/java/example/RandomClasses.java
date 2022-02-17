package example;

import io.d2a.eeee.Starter;
import io.d2a.eeee.annotations.Entrypoint;
import io.d2a.eeee.annotations.generate.Generate;
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

        public Person(
            @Generate(generator = RandomNameGenerator.class) final String name,
            @Generate @Max(100) final int age
        ) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return String.format("%s ist %d Jahre alt", this.name, this.age);
        }

    }

    @Entrypoint
    public void run() throws Exception {
        final Person[] persons = new Person[5];
        RandomFactory.fillArrayRandom(persons, Person.class);
        Stream.of(persons).forEach(System.out::println);
    }

}
