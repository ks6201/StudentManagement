package org.assignment.cli.libs.menu;

import org.assignment.cli.libs.console.IConsole;

import java.util.Scanner;
import java.util.function.BiConsumer;

public class TerminalMenuNode extends MenuNode {
    public MenuNode lastMenuNode;
    public final BiConsumer<IConsole, Scanner> action;

    public TerminalMenuNode(String title, BiConsumer<IConsole, Scanner> action) {
        this.title = title;
        this.action = action;
    }

    @Override
    public String toString() {
        return "TerminalMenuNode{" +
                "action=" + action +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public void execute(ListingMenuNode prevNode) {
        this.lastMenuNode = prevNode;
        this.action.accept(this.console, this.scanner);

        this.console.print("\nPress enter key to continue...");
        this.scanner.nextLine();

        this.lastMenuNode.execute(prevNode.lastMenuNode);
    }
}