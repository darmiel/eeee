package example.prompt;

import io.d2a.eeee.Starter;
import io.d2a.eeee.annotation.annotations.prompt.Entrypoint;

public class OneMethodExample {

    public static void main(String[] args) throws Exception {
        Starter.start(OneMethodExample.class, args);
    }

    @Entrypoint
    public void run() {
        System.out.println("Hello World!");
    }

}
