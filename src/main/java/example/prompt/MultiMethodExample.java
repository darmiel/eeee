package example.prompt;

import io.d2a.eeee.Starter;
import io.d2a.eeee.annotation.annotations.prompt.Entrypoint;

public class MultiMethodExample {

    public static void main(String[] args) throws Exception {
        Starter.start(MultiMethodExample.class, args, true);
    }

    private int a;

    @Entrypoint
    public void a() {
        System.out.println("Ran a and set a to 12 from " + this.a + "!");
        this.a = 12;
    }

    @Entrypoint
    public void b() {
        System.out.println("Ran b and set a to 5 from " + this.a + "!");
        this.a = 5;
    }

}
