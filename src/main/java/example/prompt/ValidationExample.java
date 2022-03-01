package example.prompt;

import io.d2a.eeee.Starter;
import io.d2a.eeee.annotation.annotations.common.Range;
import io.d2a.eeee.annotation.annotations.common.Transform;
import io.d2a.eeee.annotation.annotations.common.Transform.Type;
import io.d2a.eeee.annotation.annotations.prompt.Default;
import io.d2a.eeee.annotation.annotations.prompt.Entrypoint;
import io.d2a.eeee.annotation.annotations.prompt.Pattern;
import io.d2a.eeee.annotation.annotations.prompt.Prompt;

public class ValidationExample {

    public static void main(String[] args) throws Exception {
        Starter.start(ValidationExample.class, args);
    }

    @Entrypoint
    public void rangeStringValidation(
        @Range({1, 3}) final String a,
        @Range(3) final String b,
        @Range({2, 8, 2}) String c
    ) {
        System.out.println("Received: " + a + ", " + b + ", " + c);
    }

    @Entrypoint
    public void rangeStringValidation(
        @Range({1, 3}) final int a,
        @Range(3) final int b,
        @Range({2, 8, 2}) int c
    ) {
        System.out.println("Received: " + a + ", " + b + ", " + c);
    }

    @Entrypoint
    public void defaultValue(
        @Prompt("Name") @Default("Daniel") final String name,
        @Prompt("Print") @Default("yes") final boolean print) {
        if (print) {
            System.out.println("Hi, " + name + "!");
        } else {
            System.out.println("Ok, bye!");
        }
    }

    @Entrypoint
    public void transform(
        @Prompt("Name") @Default("Daniel")
        @Transform({Type.REVERSE}) final String name) {
        System.out.println("!" + name + ", iH");
    }

    @Entrypoint
    public void chars(@Prompt("Char?") final char c) {
        System.out.println(c);
    }

    @Entrypoint
    public void stringPattern(
        @Prompt("Name") @Pattern("^[a-hA-H]+$") final String name
    ) {
        System.out.printf("Hi, %s!%n", name);
    }

}
