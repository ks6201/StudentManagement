package org.assignment.core.libs.validator.Pattern;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Pattern {
    String value();
    String errorMessage() default "Provided value for field '{f}' doesn't match required pattern.";
}
