package example.prompt;

import io.d2a.eeee.PromptFactory;
import io.d2a.eeee.Starter;
import io.d2a.eeee.annotation.annotations.common.Range;
import io.d2a.eeee.annotation.annotations.generate.Fill;
import io.d2a.eeee.annotation.annotations.generate.Generate;
import io.d2a.eeee.annotation.annotations.prompt.Entrypoint;
import io.d2a.eeee.annotation.annotations.prompt.Factory;
import io.d2a.eeee.inject.Inject;
import java.util.Arrays;
import java.util.Scanner;

public class PromptFactoryExample {

    public interface PromptInterface {

        @Range({1, 10})
        int requestAge();

        @Generate
        @Fill(4)
        int[] generateNumbers();

        @Inject
        Scanner getScanner();

    }

    @Entrypoint(verbose = true)
    public void run (
        @Factory final PromptInterface pi
    ) {
        System.out.println("Generated Numbers: " + Arrays.toString(pi.generateNumbers()));
        System.out.println("Your Age: " + pi.requestAge());
        System.out.print("Type anything: ");
        System.out.println("You typed: " + pi.getScanner().nextLine());
    }

    /*
     * Generated Numbers: [6, 9, 6, 6]
     * [int] [1-10] requestAge > 1
     * Your Age: 1
     * Type anything: test
     * You typed: test
     */
    public static void main(String[] args) {
        // using entrypoint
        Starter.byCaller();

        // or create directly:
        final PromptInterface pi = PromptFactory.build(PromptInterface.class);
    }

}
