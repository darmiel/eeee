package example.prompt;

import io.d2a.eeee.EntryPointCollection;
import io.d2a.eeee.Starter;
import io.d2a.eeee.annotation.annotations.prompt.Entrypoint;
import io.d2a.eeee.annotation.annotations.prompt.Prompt;
import io.d2a.eeee.inject.Inject;
import io.d2a.eeee.prompt.Call;

public class CallExample {

    public static void main(String[] args) throws Exception {
        Starter.start(CallExample.class, args);
    }

    // Method 1: Using Call<?>
    @Entrypoint
    public void a(
        @Entrypoint("__get_name") final Call<String> getName
    ) {
        System.out.println("Hi, " + getName.call());
    }

    // Method 2: Manually calling method using EntryPointCollection
    @Entrypoint
    public void b(
        @Inject final EntryPointCollection epc
    ) throws Exception {
        final String name = (String) epc.invoke(epc.select("__get_name"));
        System.out.println("Hi, " + name);
    }

    ///

    // Helper Entry Point for `a` and `b`
    @Entrypoint(value = "__get_name")
    public String getName(@Prompt("Your Name") final String name) {
        return name;
    }

}
