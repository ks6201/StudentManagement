package org.assignment.core.libs.validator.Range;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RangeDouble {
    double min() default Double.MIN_VALUE;

    double max() default 0.0;

    String errorMessage() default "Provided value for field '{f}' doesn't match required range.";
}
