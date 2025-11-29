package org.assignment.core.libs.validator;


import org.assignment.core.libs.validator.CustomValidator.CustomValidator;
import org.assignment.core.libs.validator.CustomValidator.CustomValidatorFn;
import org.assignment.core.libs.validator.Length.Length;
import org.assignment.core.libs.validator.Length.LengthValidator;
import org.assignment.core.libs.validator.Optional.Optional;
import org.assignment.core.libs.validator.Pattern.Pattern;
import org.assignment.core.libs.validator.Pattern.PatternValidator;
import org.assignment.core.libs.validator.Range.Range;
import org.assignment.core.libs.validator.Range.RangeDouble;
import org.assignment.core.libs.validator.Range.RangeValidator;
import org.assignment.core.libs.validator.Required.Required;
import org.assignment.core.libs.validator.Required.RequiredValidator;
import org.assignment.core.libs.validator.exceptions.ValidationFailedException;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;

public final class Validator {

    private static void validateAux(Class<?> clazz, Object object) {

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);

            try {
                Object value = field.get(object);
                Class<?> type = field.getType();

                boolean isOptional = field.isAnnotationPresent(Optional.class);

                Validator.hasRequiredValidator(isOptional, field, value);

                if (isOptional && value == null) {
                    return;
                }

                Validator.hasPatternValidator(isOptional, field, value);

                Validator.hasLengthValidator(isOptional, field, type, value);

                Validator.hasCustomValidator(isOptional, field, value);

                Validator.hasRangeValidator(isOptional, field, type, value);

                Validator.hasRangeDoubleValidator(isOptional, field, type, value);

            } catch (ValidationFailedException e) {
                throw e;
            } catch (Exception ex) {
                throw new RuntimeException("Something went wrong while validating " + field.getName(), ex);
            }
        }
    }

    private static void hasRangeDoubleValidator(boolean isOptional, Field field, Class<?> type, Object value) {
        if (!field.isAnnotationPresent(RangeDouble.class)) return;

        RangeDouble ann = field.getAnnotation(RangeDouble.class);
        double min = ann.min();
        double max = ann.max();

        var fieldName = field.getName();

        if (type != Double.class) {
            throw new ValidationFailedException(
                    fieldName,
                    "'" + fieldName + "' Unsupported data type."
            );
        }

        boolean wasSuccess = RangeValidator.validate((double) value, min, max);

        if (!wasSuccess && !isOptional) {

            String message = ann.errorMessage();

            if(message.contains("'{f}'")) {
                message = message.replace("{f}", fieldName);
            }

            throw new ValidationFailedException(fieldName, message);
        }
    }

    private static void hasRangeValidator(boolean isOptional, Field field, Class<?> type, Object value) {
        if (!field.isAnnotationPresent(Range.class)) return;

        Range annotation = field.getAnnotation(Range.class);

        long min = annotation.min();
        long max = annotation.max();

        var fieldName = field.getName();
        if (type != Integer.class && type != Long.class) {
            throw new ValidationFailedException(
                    fieldName,
                    "'" + field.getName() + "' Unsupported data type."
            );
        }

        assert value instanceof Integer;
        boolean wasSuccess = RangeValidator.validate((Integer) value, min, max);

        if (!wasSuccess && !isOptional) {

            String message = annotation.errorMessage();

            if(message.contains("'{f}'")) {
                message = message.replace("{f}", fieldName);
            }

            throw new ValidationFailedException(fieldName, message);
        }
    }

    private static void hasCustomValidator(boolean isOptional, Field field, Object value) {

        if (!field.isAnnotationPresent(CustomValidator.class)) return;

        CustomValidator annotation = field.getAnnotation(CustomValidator.class);

        var fn = annotation.cls();
        var fieldName = field.getName();

        try {
            CustomValidatorFn obj = fn.getDeclaredConstructor().newInstance();
            var wasSuccess = obj.validate(value);

            if (!wasSuccess && !isOptional) {
                String message = annotation.errorMessage();

                if(message.contains("'{f}'")) {
                    message = message.replace("{f}", fieldName);
                }

                throw new ValidationFailedException(fieldName, message);
            }
        } catch (ValidationFailedException e) {
            throw e;
        } catch (Exception e) {
            throw new ValidationFailedException(
                    fieldName,
                    "'" + fieldName + "' Bad 'class' passed."
            );
        }
    }

    private static void hasLengthValidator(
            boolean isOptional,
            Field field,
            Class<?> type,
            Object value
    ) {
        if (!field.isAnnotationPresent(Length.class)) return;

        Length annotation = field.getAnnotation(Length.class);
        long min = annotation.min();
        long max = annotation.max();

        var fieldName = field.getName();

        boolean wasSuccess;
        if (value instanceof CharSequence) {
            wasSuccess = LengthValidator.validate(
                    ((String) value).length(),
                    min, max
            );
        } else if (
                type == Integer.class || type == Long.class
        ) {
            wasSuccess = LengthValidator.validate(
                    value.toString().length(),
                    min, max
            );
        } else if (value.getClass().isArray()) {
            wasSuccess = LengthValidator.validate(
                    Array.getLength(value),
                    min, max
            );
        } else if (value instanceof Collection) {
            wasSuccess = LengthValidator.validate(
                    ((Collection<?>) (value)).size(),
                    min, max
            );
        } else {
            throw new ValidationFailedException(
                    fieldName,
                    "Unsupported type: " + "'" + type + "'"
            );
        }

        if (!wasSuccess && !isOptional) {
            String message = annotation.errorMessage();
            if(message.contains("'{f}'")) {
                message = message.replace("{f}", fieldName);
            }

            throw new ValidationFailedException(fieldName, message);
        }
    }

    private static void hasPatternValidator(
            boolean isOptional,
            Field field,
            Object value
    ) {
        if (!field.isAnnotationPresent(Pattern.class)) return;

        Pattern annotation = field.getAnnotation(Pattern.class);
        String pattern = annotation.value();
        var wasSuccess = PatternValidator.validate((String) value, pattern);
        if (!wasSuccess && !isOptional) {
            String message = annotation.errorMessage();
            var fieldName = field.getName();

            if(message.contains("'{f}'")) {
                message = message.replace("{f}", fieldName);
            }

            throw new ValidationFailedException(fieldName, message);
        }
    }

    private static void hasRequiredValidator(
            boolean isOptional,
            Field field,
            Object value
    ) {
        if (isOptional || !field.isAnnotationPresent(Required.class)) {
            return;
        }

        var annotation = field.getAnnotation(Required.class);

        boolean wasSuccess = RequiredValidator.validate(value);
        if (!wasSuccess) {
            String message = annotation.errorMessage();
            var fieldName = field.getName();
            if(message.contains("'{f}'")) {
                message = message.replace("{f}", fieldName);
            }
            throw new ValidationFailedException(fieldName, message);
        }
    }

    public static void validate(Object object) {
        Class<?> clazz = object.getClass();

        while (clazz != Object.class) {
            Validator.validateAux(clazz, object);
            clazz = clazz.getSuperclass();
        }
    }
}
