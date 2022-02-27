package example.prompt;

import io.d2a.eeee.Starter;
import io.d2a.eeee.annotation.annotations.Entrypoint;
import io.d2a.eeee.annotation.annotations.Fill;
import io.d2a.eeee.annotation.annotations.Generate;
import io.d2a.eeee.annotation.annotations.Prompt;
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
        @Generate @Fill(10) final Test[] testGen,
        @Inject(create = true) Test testInj
    ) {
        System.out.println(line);
        System.out.println(Arrays.toString(testGen));
        System.out.println(testInj);
    }

}
