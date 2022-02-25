package io.d2a.eeee.annotation.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Entrypoint {

    String value() default "Default";

    boolean loop() default false;

    boolean verbose() default false;

    boolean stopwatch() default false;

}
