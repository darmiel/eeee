package example.generate;

import io.d2a.eeee.Starter;
import io.d2a.eeee.annotation.annotations.Entrypoint;
import io.d2a.eeee.annotation.annotations.Prompt;
import io.d2a.eeee.generate.prompt.PromptFactory;
import java.util.Scanner;

public class FactoryExample {

    public static void main(String[] args) throws Exception {
        Starter.start(FactoryExample.class, args);
    }

    @Entrypoint
    public void a(Scanner scanner) throws Exception {
        System.out.println(PromptFactory.createClass(scanner, Person.class));
    }

    public static class Person {

        @Prompt()
        private String name;
        @Prompt()
        private int age;

        private Person() {

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
