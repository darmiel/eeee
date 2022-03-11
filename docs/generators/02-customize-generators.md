# Customize Generators

The generated values can be adjusted using the following annotations.

!!! note ""
    If an annotation is written above the constructor, it is usually applied to **all parameters within the constructor**, should the parameter not have its own annotation.

## `@Use`

With the Use Annotation a specific generator can be selected.

!!! note ""
    When `@Use` is applied to an array, the specified generator is applied to the **individual values** within the array.


!!! tip "(String) Name Generator"

    EEEE has a generator for (human) names: `NameGenerator`

    ```java
    @Generate
    public MyClass(@Use(NameGenerator.class) final String name) {
        // could be James, Mary, John, ...
    }
    ```

---

## `@Range`

Limits the size of the generated value.

@Range({min, max, step})

!!! tip "Steps"
    
    For generators, a third argument can be used: the step. 
    This specifies the steps in which the number is to be generated. 
    This is very helpful with doubles, for example, to limit the precision.

    ```java
    public MyClass(
        @Range(0, 1, 0.2) final double value
    ) {
        // value could be 0, 0.2, 0.4, 0.6, 0.8, and 1
    }
    ```

---

## `@Transform`

Transforms a generated string. Same as the `@Transform`-annotation in the [Validation](../entrypoints/03-validation.md) section.