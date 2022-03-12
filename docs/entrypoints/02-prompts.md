# Prompts

Any number of parameters can be passed to an entrypoint. 
The user is then asked to enter these parameters when the program is started.

Use the `@Prompt` annotation to change the prompt.

```java
@Entrypoint
public void run(@Prompt("Enter your name") final String name) {
    System.out.printf("Hello %s!%n", name);
}
```

```
[String] Enter your name > Daniel

Hello Daniel!
```

???+ example "Natively Supported Types"

    - [x] boolean / Boolean
    - [x] char / Character
    - [x] double / Double
    - [x] int / Integer
    - [x] short / Short
    - [x] String

    **Special Wrappers:**

    - [x] Arrays of the above types
    - [x] Scanner

---

## Default Values

Default values can be set for parameters using the `@Default`-annotation:

```java
@Entrypoint
public void run(@Prompt("Name") @Default("Simon") final String name) {
    System.out.printf("Hello %s!%n", name);
}
```

If no value is entered, the default value is used.

```
[String] Name [Simon] > (enter)
Hello Simon!
```

!!! bug "Note"
    Only inputs as string are possible. Enter as default value exactly what you expect from the user.


---

## Arrays

(see [ArrayPromptExample.java](https://github.com/darmiel/eeee/blob/main/src/main/java/example/prompt/ArrayPromptExample.java))

As announced above, it is possible to pass arrays. 
An array is then filled with values entered by the user with a separator (comma by default):

```java
@Entrypoint
public void run(@Prompt("Enter Numbers") @Split(" ") final int[] numbers) {
    System.out.println(Arrays.toString(numbers));
}
```

```
[arr<int>] / / Enter Numbers > 1 8 2 9 3
[1, 8, 2, 9, 3]
```

!!! bug "Note"
    Validators **do not work** for values inside the array. 
    For example, `@Range` only limits the array size, not the individual values.
