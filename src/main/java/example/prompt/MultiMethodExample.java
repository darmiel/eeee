package example.prompt;

import io.d2a.eeee.Starter;
import io.d2a.eeee.annotation.annotations.prompt.Entrypoint;

public class MultiMethodExample {

    public static void main(String[] args) throws Exception {
        Starter.start(MultiMethodExample.class, args);
    }

    @Entrypoint
    public void a() {
        System.out.println("Ran a!");
    }

    @Entrypoint
    public void b() {
        System.out.println("Ran b!");
    }

}
