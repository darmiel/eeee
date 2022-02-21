# Generator

![prev](assets/generator-action.gif)

Sample objects can be created with the help of the `RandomFactory`. To do this, any constructor in
the target class must be annotated with `@Generate`.

**NOTE:** make sure all parameters can be generated.

```java
// define person class
public class Person {

    private final String name;
    private final int age;

    @Generate
    public Person(final String name, final int age) {
        this.name = name;
        this.age = age;
    }

    // ...
}

// application
public class App {

    @Entrypoint
    public void run() {
        final Person person = RandomFactory.generate(Person.class);
        System.out.println(person); // Person{name='AhgrEgVB', age=9}
    }

}
```

### Customize generator

The number ranges and string lengthes can be adjusted with `@Range`.

```java
class Person {

    @Generate
    public Person(final String name, @Range(100) final int age) {
        this.name = name;
        this.age = age;
    }

    // or
    @Generate
    @Range(100) // applied for a and b
    public Person(final int a, final int b) {
        // ...
    }

}
```

You can implement your own generators by implementing the Generator<T>
interface in the class to be generated.

```java
public class A implements Generator<A> {

    @Override
    public A generate(final Random random, final AnnotationProvider provider) {
        return new A(); // generate something
    }

}

public class B {

    @Generate
    public B(final A a) {
        // ...
    }

}
```

Generators can be overridden for certain parameters using `@Use`:

```java
class Person {

    @Generate
    public Person(
        @Use(NameGenerator.class) final String name,
        @Range(100) final int age
    ) {
        this.name = name;
        this.age = age;
    }

}
// RandomFactory.createRandom(Person.class) produces:
// Person{name='Gary', age=54}
```
