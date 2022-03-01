package example.prompt;

import io.d2a.eeee.Starter;
import io.d2a.eeee.annotation.annotations.common.Use;
import io.d2a.eeee.annotation.annotations.generate.Fill;
import io.d2a.eeee.annotation.annotations.generate.Generate;
import io.d2a.eeee.annotation.annotations.prompt.Entrypoint;
import io.d2a.eeee.annotation.annotations.prompt.Prompt;
import io.d2a.eeee.generate.random.generators.special.NameGenerator;
import io.d2a.eeee.inject.Inject;
import java.util.Arrays;
import java.util.Scanner;

public class LoopExample {

    public static void main(String[] args) {
        Starter.byCaller();
    }

    public static class Test {
        private final Scanner scanner;
        private final String name;

        @Generate
        public Test(@Inject final Scanner scanner, final String nameA) {
            this.scanner = scanner;
            this.name = nameA == null ? "injected" : nameA;
        }

        @Override
        public String toString() {
            return "Test{" +
                "scanner=" + (scanner == null ? "null" : "exists") +
                ", name='" + name + '\'' +
                '}';
        }
    }

    @Entrypoint(loop = true)
    public void run(
        @Prompt("_") final String line,
        @Generate @Fill(10) @Use(NameGenerator.class) final String[] testGen,
        @Inject(create = true) Test testInj
    ) {
        System.out.println(line);
        System.out.println(Arrays.toString(testGen));
        System.out.println(testInj);
    }

}
