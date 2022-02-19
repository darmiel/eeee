package example.prompt;

import io.d2a.eeee.Starter;
import io.d2a.eeee.annotation.annotations.Entrypoint;
import io.d2a.eeee.annotation.annotations.ForceRun;

/**
 * b should always be called at startup
 */
public class MultiMethodForceExample {

    public static void main(String[] args) throws Exception {
        Starter.start(MultiMethodForceExample.class, args);
    }

    @Entrypoint
    public void a() {
        System.out.println("Ran a!");
    }

    @Entrypoint @ForceRun
    public void b() {
        System.out.println("Ran b!");
    }

}
