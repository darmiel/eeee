package example.plainvseeee;

import io.d2a.eeee.annotation.annotations.generate.Generate;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Goal: Read amount of Person objects to generate
 */
public class SimplePlainVsEeee {

    public static class Plain {

        public static class Person {

            private final String name;
            private final int age;

            @Generate
            public Person(final String name, final int age) {
                this.name = name;
                this.age = age;
            }

            @Override
            public String toString() {
                return "Person{" + "name='" + name + '\'' + ", age=" + age + '}';
            }

        }

        private static final char[] charset = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz"
            .toCharArray();

        public static void main(String[] args) {
            final Scanner scanner = new Scanner(System.in);

            // read amount of person objects to generate
            int amount;
            do {
                System.out.print("How many people should be generated? [Default: 5] ");
                final String line = scanner.nextLine().trim();
                if (line.isEmpty()) { // default value: 5
                    amount = 5;
                    break;
                }
                try {
                    amount = Integer.parseInt(line);
                    if (amount < 1 || amount > 100) { // check bounds
                        System.out.println("Number must be in range 1 to 100!");
                        continue;
                    }
                    break;
                } catch (final NumberFormatException nfex) {
                    System.out.println("Input must be a number!");
                }
            } while (true);

            // generate n person objects
            final Random random = new Random();
            final Person[] people = new Person[amount];
            for (int i = 0; i < amount; i++) {
                // generate name of length 5 to 10
                final StringBuilder name = new StringBuilder();
                final int nameLength = random.nextInt(5) + 5;
                for (int j = 0; j < nameLength; j++) {
                    name.append(charset[random.nextInt(charset.length)]);
                }

                // generate parents
                final String[] parents = new String[2];
                for (int j = 0; j < parents.length; j++) {
                    final StringBuilder parentName = new StringBuilder();
                }

                // generate age
                final int age = random.nextInt(100) + 1;
                people[i] = new Person(name.toString(), age);
            }
            System.out.println("Generated: " + Arrays.toString(people));
        }

    }


}
