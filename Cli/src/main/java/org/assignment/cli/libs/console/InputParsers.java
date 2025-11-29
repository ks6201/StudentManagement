package org.assignment.cli.libs.console;

import java.util.Map;
import java.util.function.Function;

public class InputParsers {
    public static final Map<InputType, Function<String, ?>> PARSERS = Map.of(
            InputType.INTEGER, (String s) -> Integer.parseInt(s.trim()),
            InputType.DOUBLE,  (String s) -> Double.parseDouble(s.trim()),
            InputType.STRING, String::trim
    );
}
