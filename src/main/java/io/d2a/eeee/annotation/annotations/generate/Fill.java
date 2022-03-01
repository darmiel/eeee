package io.d2a.eeee.annotation.annotations.generate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({
    ElementType.FIELD,
    ElementType.PARAMETER,
    ElementType.CONSTRUCTOR,
    ElementType.METHOD
})
@Retention(RetentionPolicy.RUNTIME)
public @interface Fill {

    /**
     * @return number of elements to fill into the array/list
     */
    int value();

}
