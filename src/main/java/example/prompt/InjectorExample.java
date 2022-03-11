package example.prompt;

import io.d2a.eeee.Starter;
import io.d2a.eeee.annotation.annotations.prompt.Entrypoint;
import io.d2a.eeee.inject.Inject;
import java.util.Scanner;

public class InjectorExample {

    public static void main(String[] args) throws Exception {
        Starter.start(ConstructorInjectorExample.class, args);
        Starter.start(FieldInjectorExample.class, args);
    }

    // Example 1: Injected object passed over parameters
    public static class InjectorParameterExample {

        @Entrypoint
        public void run(@Inject final Scanner scanner) {
            InjectorExample.run(scanner);
        }

        // Example 1.1: Create new object and inject into constructor
        @Entrypoint
        public void run2(@Inject(create = true) final Test test) {
            System.out.println("Created Test class: " + test.toString());
        }

        private static class Test {

            private final Scanner scanner;
            private final int a;
            private final int b;

            public Test(@Inject final Scanner scanner) {
                this.scanner = scanner;
                this.a = 10;
                this.b = 15;
            }

            @Override
            public String toString() {
                return "Test{" + "scanner=" + scanner + ", a=" + a + ", b=" + b + '}';
            }
        }

    }

    // ---------------------------------------------------------------------------------

    // Example 2: Inject values in constructor
    public static class ConstructorInjectorExample {

        private final Scanner scanner;

        public ConstructorInjectorExample(@Inject final Scanner scanner) {
            this.scanner = scanner;
        }

        @Entrypoint
        public void run() {
            InjectorExample.run(this.scanner);
        }

    }

    // ---------------------------------------------------------------------------------

    // Example 3: Inject values in fields
    public static class FieldInjectorExample {

        @Inject
        private Scanner scanner;

        @Entrypoint
        public void run() {
            InjectorExample.run(this.scanner);
        }

    }

    // Helper Method for examples above
    private static void run(final Scanner scanner) {
        final String echo = scanner.nextLine();
        System.out.printf("Echo! %s%n", echo);
    }

}
