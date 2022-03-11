# Prompt Interface

(see [PromptFactoryExample.java](https://github.com/darmiel/eeee/blob/main/src/main/java/example/prompt/PromptFactoryExample.java))

An alternative would be to create an interface, write the prompts there, and then pass this interface using the @Factory annotation:

```java
public interface Wiz {
    @Prompt("Enter Name")
    String getName();
}

@Entrypoint
public void run(@Factory final Wiz wiz) {
    for (int i = 0; i < 5; i++) {
        System.out.println("Name " + (i + 1) + ": " + wiz.getName());
    }
}
```