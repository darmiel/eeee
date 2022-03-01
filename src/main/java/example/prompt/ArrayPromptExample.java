package example.prompt;

import io.d2a.eeee.Starter;
import io.d2a.eeee.annotation.annotations.common.Range;
import io.d2a.eeee.annotation.annotations.common.Transform;
import io.d2a.eeee.annotation.annotations.common.Transform.Type;
import io.d2a.eeee.annotation.annotations.prompt.Entrypoint;
import io.d2a.eeee.annotation.annotations.prompt.Pattern;
import io.d2a.eeee.annotation.annotations.prompt.Prompt;
import io.d2a.eeee.annotation.annotations.prompt.Split;
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
