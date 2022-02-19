package example;

import io.d2a.eeee.Starter;
import io.d2a.eeee.annotation.annotations.Entrypoint;
import io.d2a.eeee.annotation.annotations.Generate;
import io.d2a.eeee.annotation.annotations.Use;
import io.d2a.eeee.annotation.annotations.Range;
import io.d2a.eeee.generate.random.RandomFactory;
import io.d2a.eeee.generate.random.generators.RandomNameGenerator;
import java.util.stream.Stream;

public class RandomClasses {

    public static void main(String[] args) throws Exception {
        Starter.start(RandomClasses.class, args);
    }

    public static class Num2D {
        private final int a;
        private final int b;

        @Generate
        public Num2D(
            @Range({1, 10}) final int a,
            @Range(1) final int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public String toString() {
            return "Num2D{" +
                "a=" + a +
                ", b=" + b +
                '}';
        }
    }

    public static class Person {
        private final String name;
        private final int age;
        private final int a;
        private final int b;

        @Generate
        public Person(
            @Use(RandomNameGenerator.class) final String name,
            final int age,
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
        final Num2D[] numbers = new Num2D[5];
        RandomFactory.fillArrayRandom(numbers);
        Stream.of(numbers).forEach(System.out::println);
    }

}
