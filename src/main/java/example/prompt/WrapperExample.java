package example.prompt;

import io.d2a.eeee.Starter;
import io.d2a.eeee.annotation.annotations.prompt.Entrypoint;
import io.d2a.eeee.annotation.annotations.prompt.Prompt;

public class WrapperExample {

    public static void main(String[] args) throws Exception {
        Starter.start(WrapperExample.class, args);
    }

    @Entrypoint
    public void echoWithArgName(
        @Prompt("What to echo?") final String b
    ) {
        System.out.println("Echo! " + b);
    }

    @Entrypoint
    public void echoNoArgName(
        final String b
    ) {
        System.out.println("Echo! " + b);
    }

}
