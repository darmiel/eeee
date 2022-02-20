package example.prompt;

import io.d2a.eeee.Starter;
import io.d2a.eeee.annotation.annotations.Entrypoint;
import io.d2a.eeee.inject.Inject;
import java.util.Scanner;

public class InjectorExample {

    public static void main(String[] args) throws Exception {
        Starter.start(ConstructorInjectorExample.class, args);
        Starter.start(FieldInjectorExample.class, args);
    }

    private static void run(final Scanner scanner) {
        final String echo = scanner.nextLine();
        System.out.printf("Echo! %s%n", echo);
    }

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

    public static class FieldInjectorExample {

        @Inject
        private Scanner scanner;

        @Entrypoint
        public void run() {
            InjectorExample.run(this.scanner);
        }

    }

}
