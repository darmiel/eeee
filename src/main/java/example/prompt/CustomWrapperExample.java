package example.prompt;

import io.d2a.eeee.Starter;
import io.d2a.eeee.annotation.annotations.Entrypoint;
import io.d2a.eeee.annotation.annotations.Prompt;
import io.d2a.eeee.generate.prompt.PromptFactory;
import io.d2a.eeee.nw.RawWrapper;
import io.d2a.eeee.nw.WrapContext;

public class CustomWrapperExample {

    public static void main(String[] args) throws Exception {
        Starter.start(CustomWrapperExample.class, args);
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

    @Entrypoint
    public void customWrapper(final Rectangle rect) {
        System.out.println("Got Rect: " + rect);
    }

}
