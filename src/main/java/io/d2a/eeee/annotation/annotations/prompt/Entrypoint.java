package io.d2a.eeee.annotation.annotations.prompt;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Entrypoint {

    String value() default "Default";

    boolean loop() default false;

    boolean verbose() default false;

    boolean stopwatch() default false;

}
