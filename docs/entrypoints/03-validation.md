# Validation

(see [ValidationExample.java](https://github.com/darmiel/eeee/blob/main/src/main/java/example/prompt/ValidationExample.java))

A few types can be validated. For numbers, for example, the default is to check whether the input is a number:

```java
@Entrypoint
public void run(@Prompt("Number of iterations") final int number) {
    for (int i = 0; i < number; i++) {
        System.out.println(i);
    }
}
```

```
[int] Number of iterations > Test
 🦑 [wrap-error] For input string: "Test"

[int] Number of iterations > 2
// OK! ...
```

???+ info "@Range"

    The size of numbers, the length of strings, and the capacity of arrays can be limited with the `@Range`-annotation:

    ```java
    @Entrypoint
    public void run(
        @Range({0, 10}) final int number,
        @Range({2, 25}) final String name
    ) {
        // ...
    }
    ```

???+ info "@Pattern"

    Strings can be validated with a regular expression using the `@Pattern`-annotation:

    ```java
    @Entrypoint
    public void run(@Pattern("^[A-Ha-h]{5}$") final String name) {
        // hbHah: allowed
        // zeZez: not allowed
    }
    ```

???+ info "@Transform"

    Strings can additionally be transformed with the `@Transform`-annotation, e.g. UPPER_CASE, lower_case and desreveR:

    ```java
    @Entrypoint
    public void run(
        @Transform(Type.UPPER) final String name
        @Transform({Type.LOWER, Type.REVERSE}) final String name2
    ) {
        // ...
    }
    ```
