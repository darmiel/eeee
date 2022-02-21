# Injection

The framework includes a Mini-Injector functionality. Injected values can be used in entrypoint
classes by default:

```java
import io.d2a.eeee.annotation.annotations.Entrypoint;
import io.d2a.eeee.inject.Inject;

public class App {

    @Inject
    private Scanner scanner; // scanner which asks for user input

    // or:
    private final String[] args;

    public App(@Inject("args") final String[] args) {
        this.args = args;
    }

    @Entrypoint
    public void hello() {
        final String line = this.scanner.nextLine();
        System.out.println(line);
    }

}
```

Default injectable values:

- Type `Scanner`, Name: None (`@Inject Scanner scanner`)
- Type: `String[]`, Name: `args` (`@Inject("args") String[] args`)

## Custom Injection

```java
import io.d2a.eeee.inject.Inject;

public class App {

    @Inject("name")
    private String name;

    private final int age;

    public App(@Inject int age) {
        this.age = age;
    }

    public static void main(String[] args) {
        final Injector injector = new Injector()
            .register(String.class, "Thorsten", "name")
            .register(int.class, 22);

        final App app = injector.create(App.class);
        app.print();
    }

    public void print() {
        System.out.printf("%s is %d.%n", this.name, this.age);
    }

}
```
