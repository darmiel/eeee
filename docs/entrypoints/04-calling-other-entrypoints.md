# Calling other Entrypoints

(see [CallExample.java](https://github.com/darmiel/eeee/blob/main/src/main/java/example/prompt/CallExample.java))

If you want to use an entrypoint e.g. as a template to give a user selection, 
an entrypoint can be called by prepending a parameter `@Entrypoint` with the **exact name** of the entrypoint to be called, 
**and** using the type `Call<type of entrypoint>`. 

The specified entrypoint will then be called when the `Call#call` is invoked:

```java
@Entrypoint("__get_name")
public String runGetName(@Prompt("Enter Name") final String name) {
    return name;
}

@Entrypoint
public void run(
    @Entrypoint("__get_name") final Call<String> nameCall
) {
    for (int i = 0; i < 5; i++) {
        System.out.println("Name " + (i + 1) + ": " + nameCall.call());
    }
}
```

```
[String] Enter Name > Daniel
Name 1: Daniel
[String] Enter Name > Test2
Name 2: Test2
[String] Enter Name > Test4
// ...
```