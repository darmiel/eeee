package io.d2a.eeee.annotation.annotations.prompt;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({
    ElementType.PARAMETER,
    ElementType.CONSTRUCTOR,
    ElementType.METHOD
})
@Retention(RetentionPolicy.RUNTIME)
public @interface Pattern {

    String value();

}
