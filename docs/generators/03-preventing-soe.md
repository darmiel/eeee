# Prevent Stack Overflow Exceptions

Let's have a look at this generator:

```java
public static class OverflowingPerson {
    private final String name;
    private final OverflowingPerson next;

    @Generate
    public OverflowingPerson(
        @Use(NameGenerator.class) final String name,
        final OverflowingPerson next
    ) {
        this.name = name;
        this.next = next;
    }
}
```

Here a StackOverflowException would occur, because `OverflowingPerson` (`next`) is generated recursively.

There are two ways to fix this:

## 1. Use `@Depth`

The `@Depth`-annotation specifies what the maximum depth may be for a generated object.

```java
@Generate
public OverflowingPerson(
    @Use(NameGenerator.class) final String name,
    @Depth(2) final OverflowingPerson next
) {
    // ...
}
```

A stack overflow exception would not occur now, because in the 2nd `OverflowingPerson` `next` is not generated, thus `null`.

## 2. Use multiple `@Generate` constructors

```java
public static class OverflowingPerson {
    private final String name;
    private final OverflowingPerson next;

    @Generate
    public OverflowingPerson(
        @Use(NameGenerator.class) final String name,
        @Generate("next") final OverflowingPerson next
    ) {
        this.name = name;
        this.next = next;
    }

    @Generate("next")
    public OverflowingPerson(
        @Use(NameGenerator.class) final String name
    ) {
        this.name = name;
        this.next = null;
    }
}
```
