package example.prompt;

import io.d2a.eeee.Starter;
import io.d2a.eeee.annotation.annotations.prompt.Entrypoint;
import io.d2a.eeee.annotation.annotations.prompt.Prompt;
import io.d2a.eeee.prompt.Call;

public class MultiMethodExample {

    public static void main(String[] args) throws Exception {
        Starter.start(MultiMethodExample.class, args, true);
    }

    @Entrypoint("get name")
    public String getName(@Prompt("Your Name") final String name) {
        return name;
    }

    private int a;

    @Entrypoint
    public void a(@Entrypoint("get name") final Call<String> getName) {
        System.out.println("Hi, " + getName.call());
        System.out.println("Ran a and set a to 12 from " + this.a + "!");
        this.a = 12;
    }

    @Entrypoint
    public void b() {
        System.out.println("Ran b and set a to 5 from " + this.a + "!");
        this.a = 5;
    }

}
