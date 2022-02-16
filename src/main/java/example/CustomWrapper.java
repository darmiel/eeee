package example;

import io.d2a.eeee.Starter;
import io.d2a.eeee.annotations.AnnotationProvider;
import io.d2a.eeee.annotations.Default;
import io.d2a.eeee.annotations.Entrypoint;
import io.d2a.eeee.annotations.parameters.Prompt;
import io.d2a.eeee.annotations.parameters.number.Min;
import io.d2a.eeee.generate.Factory;
import io.d2a.eeee.wrapper.Wrapper;
import java.util.Scanner;

public class CustomWrapper {

    public static void main(String[] args) throws Exception {
        Starter.start(CustomWrapper.class, args);
    }

    public static class Rectangle implements Wrapper<Rectangle> {

        @Prompt("Height")
        @Default("12")
        @Min(0)
        private int height;

        @Prompt("Width")
        @Min(0)
        private int width;

        @Override
        public Rectangle wrap(
            final Scanner scanner,
            final String prompt,
            final AnnotationProvider provider
        ) {
            try {
                return Factory.createClass(scanner, Rectangle.class);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        public String toString() {
            return "Rectangle{" +
                "height=" + height +
                ", width=" + width +
                '}';
        }

    }

    @Entrypoint("Echo")
    public void echo(@Prompt("Rect") final Rectangle rectangle) {
        System.out.println(rectangle);
    }

}
