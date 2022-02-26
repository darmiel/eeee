package example.prompt;

import io.d2a.eeee.Starter;
import io.d2a.eeee.annotation.annotations.Entrypoint;
import io.d2a.eeee.annotation.annotations.Pattern;
import io.d2a.eeee.annotation.annotations.Prompt;
import io.d2a.eeee.annotation.annotations.Range;
import io.d2a.eeee.annotation.annotations.Split;
import io.d2a.eeee.annotation.annotations.Transform;
import io.d2a.eeee.annotation.annotations.Transform.Type;
import java.util.Arrays;

public class ArrayPromptExample {

    public static void main(String[] args) throws Exception {
        Starter.start(ArrayPromptExample.class, args);
    }

    @Entrypoint
    public void run(
        @Prompt("Personen") @Split(",") @Range({2, 3}) @Pattern("^[A-Ha-h]$")
        final String[] persons
    ) {
        System.out.println(Arrays.toString(persons));
    }

    @Entrypoint
    public void run2(
        @Split(",") @Range({2, 3}) @Transform(Type.UPPER)
        final String[] names
    ) {
        System.out.println(Arrays.toString(names));
    }

}
