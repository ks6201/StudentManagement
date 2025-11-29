package org.assignment.core.libs.validator.exceptions;

public class ValidationFailedException extends RuntimeException {
    private final String fieldName;
    public ValidationFailedException(
            String fieldName,
            String message
    ) {
        super("ValidationFailedException: " + message);
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}