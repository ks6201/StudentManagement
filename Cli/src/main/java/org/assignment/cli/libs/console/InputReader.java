package org.assignment.cli.libs.console;

import java.util.Scanner;

@FunctionalInterface
public interface InputReader<T> {
    T read(Scanner scanner);
}