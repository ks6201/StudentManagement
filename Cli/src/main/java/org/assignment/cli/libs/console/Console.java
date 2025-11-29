package org.assignment.cli.libs.console;

import java.util.Scanner;
import java.util.function.Function;

public class Console implements IConsole {
    private int linesPrinted = 0;

    public boolean supportsAnsi() {
        return System.console() != null;
    }

    @Override
    public void print(String... message) {
        for (String m : message) {
            System.out.print(m);
        }
    }

    @Override
    public void println(String... message) {
        this.print(message);
        System.out.println();
        this.linesPrinted++;
    }

    @Override
    public void clearScreen() {
        if (this.supportsAnsi()) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        } else {
            for (int i = 0; i < this.linesPrinted + 100; i++) System.out.println();
        }
        this.linesPrinted = 0;
    }

    public static <T> T getUserInputFor(
            String prompt,
            IConsole console,
            Scanner scanner,
            InputType type
    ) {
        return Console.getUserInputFor(prompt, console, scanner, type, false);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getUserInputFor(
            String prompt,
            IConsole console,
            Scanner scanner,
            InputType type,
            boolean allowEmpty
    ) {
        Function<String, ?> parser = InputParsers.PARSERS.get(type);

        while (true) {
            console.print(prompt);

            String raw = scanner.nextLine();

            if (allowEmpty && raw.trim().isEmpty())
                return null;
            if (!allowEmpty && raw.trim().isEmpty())
                continue;

            try {
                return (T) parser.apply(raw);
            } catch (Exception e) {
                if (allowEmpty) return null;
                continue;
            }
        }
    }
}
