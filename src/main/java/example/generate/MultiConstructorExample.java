package example.generate;

import io.d2a.eeee.Starter;
import io.d2a.eeee.annotation.annotations.generate.Generate;
import io.d2a.eeee.annotation.annotations.prompt.Entrypoint;
import io.d2a.eeee.generate.random.RandomFactory;

public class MultiConstructorExample {

    public static void main(String[] args) throws Exception {
        Starter.start(MultiConstructorExample.class, args);
    }

    @Entrypoint
    public void run() throws Exception {
        final Hello hello = RandomFactory.generate(Hello.class, "");
        System.out.println(hello);
    }

    public static class Hello {

        private final String value;
        private final Hello hello;

        @Generate
        public Hello(int a, @Generate("str") Hello b) {
            this.value = "int";
            this.hello = b;
        }

        @Generate("str")
        public Hello(String b) {
            this.value = "string";
            this.hello = null;
        }

        @Override
        public String toString() {
            return "Hello{" +
                "value='" + value + '\'' +
                ", hello=" + hello +
                '}';
        }
    }

}
