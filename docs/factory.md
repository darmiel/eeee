## Factory

With the help of the Factory class you can now simplify the creation of a "pojo" class:

```java
import io.d2a.eeee.inject.Inject;

public class Rectangle {

    @Prompt("Height")
    private int height;

    @Prompt("Width")
    private int width;

    // or using the constructor for more control:
    @Prompt
    public Rectangle(
        @Prompt("Height") final int height,
        @Prompt("Width")
        ) {
        this.height = height;
        this.width = width;
    }

}

public class App {

    @Entrypoint("Create Rectangle")
    public void echo(final Scanner scanner) {
        Rectangle rect = Factory.createClass(scanner, Rectangle.class);
        System.out.println(rect);
    }

}
```

---
