package example.prompt;

import io.d2a.eeee.Starter;
import io.d2a.eeee.annotation.annotations.Entrypoint;
import io.d2a.eeee.annotation.annotations.Prompt;
import io.d2a.eeee.annotation.provider.AnnotationProvider;
import io.d2a.eeee.generate.Factory;
import io.d2a.eeee.wrapper.Wrapper;
import java.util.Scanner;

public class CustomWrapperExample {

    public static void main(String[] args) throws Exception {
        Starter.start(CustomWrapperExample.class, args);
    }

    public static class Rectangle implements Wrapper<Rectangle> {
        @Prompt("Height")
        private int height;

        @Prompt("Width")
        private int width;

        @Override
        public String toString() {
            return "Rectangle{" +
                "height=" + height +
                ", width=" + width +
                '}';
        }

        @Override
        public Rectangle wrap(
            final Scanner scanner,
            final String prompt,
            final AnnotationProvider provider
        ) throws Exception {
            return Factory.createClass(scanner, Rectangle.class);
        }
    }

    @Entrypoint
    public void customWrapper(final Rectangle rect) {
        System.out.println("Got Rect: " + rect);
    }

}
