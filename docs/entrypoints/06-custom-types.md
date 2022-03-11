# Custom Types

If you want to have your own class created using a prompt, you can solve this by implementing the `RawWrapper<T>` interface.

```java
public static class Rectangle implements RawWrapper<Rectangle> {

    private final int height;
    private final int width;

    // ...

    @Override
    public Rectangle wrap(final WrapContext ctx) {
        System.out.println("Rectangle#wrap called!");
        return /* ... */;
    }
}

@Entrypoint
public void run(final Rectangle rect) {
    System.out.println(rect);
}
```

```
Rectangle#wrap called!
// ...
```