package example.prompt;

import io.d2a.eeee.Starter;
import io.d2a.eeee.annotation.annotations.Entrypoint;
import io.d2a.eeee.annotation.annotations.Prompt;
import io.d2a.eeee.annotation.annotations.Range;
import io.d2a.eeee.annotation.annotations.Split;
import java.util.Arrays;

public class ArrayPromptExample {

    public static void main(String[] args) throws Exception {
        Starter.start(ArrayPromptExample.class, args);
    }

    @Entrypoint
    public void run(
        @Prompt("Personen") @Split(",") @Range({2, 3})
        final String[] persons
    ) {
        System.out.println(Arrays.toString(persons));
    }

}
