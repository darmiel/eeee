package example.prompt;

import io.d2a.eeee.Starter;
import io.d2a.eeee.annotation.annotations.Default;
import io.d2a.eeee.annotation.annotations.Entrypoint;
import io.d2a.eeee.annotation.annotations.ForceRun;
import io.d2a.eeee.annotation.annotations.Prompt;
import io.d2a.eeee.annotation.annotations.Range;
import io.d2a.eeee.annotation.annotations.Transform;
import io.d2a.eeee.annotation.annotations.Transform.Type;

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
    @ForceRun
    public void transform(
        @Prompt("Name") @Default("Daniel")
        @Transform({Type.REVERSE}) final String name) {
        System.out.println("!" + name + ", iH");
    }

}
