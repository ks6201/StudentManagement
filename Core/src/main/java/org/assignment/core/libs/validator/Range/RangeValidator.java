package org.assignment.core.libs.validator.Range;

public class RangeValidator {
    public static boolean validate(int value, long min, long max) {
        if (value < min) return false;
        return max == -1 || value <= max;
    }

    public static boolean validate(long value, long min, long max) {
        if (value < min) return false;
        return max == -1 || value <= max;
    }

    public static boolean validate(double value, double min, double max) {
        if (value < min) return false;
        return max == 0.0 || value <= max;
    }
}
