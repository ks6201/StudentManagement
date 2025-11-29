package org.assignment.core.libs.validator.Pattern;

import java.util.regex.Pattern;

public class PatternValidator {
    public static boolean validate(String text, String regex) {
        return Pattern.matches(regex, text);
    }
}
