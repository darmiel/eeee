package io.d2a.eeee.annotations.generate;

import io.d2a.eeee.generate.placeholder.Generator;
import io.d2a.eeee.generate.placeholder.generators.DummyGenerator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Generate {

    Class<? extends Generator> generator() default DummyGenerator.class;

}
