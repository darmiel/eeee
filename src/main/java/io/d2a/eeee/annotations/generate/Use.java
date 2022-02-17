package io.d2a.eeee.annotations.generate;

import io.d2a.eeee.generate.placeholder.generators.DummyGenerator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface Use {

    Class<?> value() default DummyGenerator.class;

}
