package example.prompt;

import io.d2a.eeee.Starter;
import io.d2a.eeee.annotation.annotations.prompt.Entrypoint;
import io.d2a.eeee.annotation.annotations.prompt.Prompt;
import io.d2a.eeee.generate.prompt.PromptFactory;
import io.d2a.eeee.prompt.RawWrapper;
import io.d2a.eeee.prompt.WrapContext;

public class CustomWrapperExample {

    public static void main(String[] args) throws Exception {
        Starter.start(CustomWrapperExample.class, args);
    }

    // For types for which no wrapper has been created yet,
    // you can implement RawWrapper, which is executed when passing in parameters
    // If there are several wrappers for one type, @Use can be used:
    // run(@Use(Rectangle.class) final Rectangle rect) { ... }
    @Entrypoint
    public void run(final Rectangle rect) {
        System.out.println("Got Rect: " + rect);
    }

    public static class Rectangle implements RawWrapper<Rectangle> {
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
        public Rectangle wrap(final WrapContext ctx) throws Exception {
            return PromptFactory.createClass(ctx.getScanner(), Rectangle.class);
        }
    }


}
