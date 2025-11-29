package org.assignment.core.libs.validator.Length;

public class LengthValidator {
    public static boolean validate(long value, long min, long max) {
        if (value < min) return false;
        return max == -1 || value <= max;
    }
}
