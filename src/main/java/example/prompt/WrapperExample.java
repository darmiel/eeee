package example.prompt;

import io.d2a.eeee.Starter;
import io.d2a.eeee.annotation.annotations.Entrypoint;
import io.d2a.eeee.annotation.annotations.Prompt;
import io.d2a.eeee.annotation.annotations.Range;
import io.d2a.eeee.annotation.annotations.Transform;
import io.d2a.eeee.annotation.annotations.Transform.Type;
import io.d2a.eeee.inject.Inject;
import java.util.Scanner;

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
