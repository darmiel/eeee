package example.prompt;

import io.d2a.eeee.Starter;
import io.d2a.eeee.annotation.annotations.Entrypoint;
import io.d2a.eeee.annotation.annotations.Prompt;

public class LoopExample {

    public static void main(String[] args) {
        Starter.byCaller();
    }

    @Entrypoint(loop = true)
    public void run(@Prompt("_") final String line) {
        System.out.println(line);
    }

}
