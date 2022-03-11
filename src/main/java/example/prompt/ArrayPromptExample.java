package example.prompt;

import io.d2a.eeee.Starter;
import io.d2a.eeee.annotation.annotations.common.Range;
import io.d2a.eeee.annotation.annotations.common.Transform;
import io.d2a.eeee.annotation.annotations.common.Transform.Type;
import io.d2a.eeee.annotation.annotations.prompt.Entrypoint;
import io.d2a.eeee.annotation.annotations.prompt.Prompt;
import io.d2a.eeee.annotation.annotations.prompt.Split;
import java.util.Arrays;

public class ArrayPromptExample {

    public static void main(String[] args) throws Exception {
        Starter.start(ArrayPromptExample.class, args);
    }

    // Example 1:
    // Input: Hello, World
    // Output: [HELLO, WORLD]
    @Entrypoint
    public void run(
        @Prompt("Personen") @Range({2, 3}) @Transform(Type.UPPER)
        final String[] persons
    ) {
        System.out.println(Arrays.toString(persons));
    }

    // Example 2:
    // Input: 827182
    // Output: [8, 2, 7, 1, 8, 2]
    @Entrypoint
    public void run2(
        @Split("")
        final int[] numbers
    ) {
        System.out.println(Arrays.toString(numbers));
    }

}
