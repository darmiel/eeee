package example.generate;

import io.d2a.eeee.Starter;
import io.d2a.eeee.annotation.annotations.Entrypoint;
import io.d2a.eeee.annotation.annotations.Prompt;
import io.d2a.eeee.annotation.annotations.Range;
import io.d2a.eeee.generate.prompt.PromptFactory;
import java.util.Scanner;

public class FactoryExample {

    public static void main(String[] args) throws Exception {
        Starter.start(FactoryExample.class, args);
    }

    @Entrypoint
    public void a(final Scanner scanner) throws Exception {
        System.out.println(PromptFactory.createClass(scanner, Person.class));
    }

    public static class Person {

        private final String name;
        private final int age;

        @Prompt
        private Person(
            @Prompt("Name") final String name,
            @Prompt("Age") @Range({0, 100}) final int age
        ) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
        }
    }

}
