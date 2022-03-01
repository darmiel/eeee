package io.d2a.eeee.annotation.annotations.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({
    ElementType.PARAMETER,
    ElementType.CONSTRUCTOR,
    ElementType.FIELD,
    ElementType.METHOD
})
@Retention(RetentionPolicy.RUNTIME)
public @interface Transform {

    interface Transformer {

        String transform(final String input);

    }

    enum Type {
        UPPER(String::toUpperCase),
        LOWER(String::toLowerCase),
        TRIM(String::trim),
        REVERSE(s -> new StringBuilder(s).reverse().toString());

        private final Transformer transformer;

        Type(final Transformer transformer) {
            this.transformer = transformer;
        }

        public String transform(final String str) {
            return this.transformer.transform(str);
        }
    }

    Type[] value();

}
