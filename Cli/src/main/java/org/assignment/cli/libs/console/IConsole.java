package org.assignment.cli.libs.console;

public interface IConsole {
    void print(String... message);
    void println(String... message);
    void clearScreen();
}
