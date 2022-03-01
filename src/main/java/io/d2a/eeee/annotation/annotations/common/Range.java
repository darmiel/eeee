package io.d2a.eeee.annotation.annotations.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 1st value: minimum, 2nd value: maximum, 3rd value: step (for checking) Example:
 * <code>@Range({1, 10})</code> numbers from 1 (inclusive) to 10 (inclusive)
 * <code>@Range({1, 10, 2})</code> numbers from 1 (inclusive) to 10 (inclusive) with steps of 2 (1,
 * 3, 5, 7, 9)
 * <code>@Range(10)</code> numbers from 0 to 10
 */
@Target({
    ElementType.FIELD,
    ElementType.PARAMETER,
    ElementType.TYPE,
    ElementType.CONSTRUCTOR,
    ElementType.METHOD
})
@Retention(RetentionPolicy.RUNTIME)
public @interface Range {

    double[] value();

}
