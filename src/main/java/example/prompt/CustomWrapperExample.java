package example.prompt;

import io.d2a.eeee.Starter;
import io.d2a.eeee.annotation.annotations.Entrypoint;
import io.d2a.eeee.annotation.annotations.Prompt;
import io.d2a.eeee.generate.Factory;
import io.d2a.eeee.nw.WrapContext;
import io.d2a.eeee.nw.Wrapper;
import io.d2a.eeee.nw.display.Component;
import io.d2a.eeee.nw.display.Components;
import io.d2a.eeee.nw.display.PromptDisplay;
import io.d2a.eeee.nw.display.StackPromptDisplay;
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
        public Rectangle wrap(final String input, final WrapContext ctx) throws Exception {
            return Factory.createClass(new Scanner(System.in), Rectangle.class);
        }

        @Override
        public PromptDisplay prompt(final WrapContext ctx) {
            return new StackPromptDisplay(
                Components.TYPE,
                Components.PROMPT,
                Components.DEFAULT
            );
        }
    }

    @Entrypoint
    public void customWrapper(final Rectangle rect) {
        System.out.println("Got Rect: " + rect);
    }

}
