package org.assignment.core.libs.validator.CustomValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CustomValidator {
    Class<? extends CustomValidatorFn> cls();
    String errorMessage() default "Provided validation fn for field '{f}' failed validation.";
}
