package org.assignment.cli.libs.menu;

import org.assignment.cli.libs.console.Console;
import org.assignment.cli.libs.console.IConsole;

import java.util.Optional;
import java.util.Scanner;

public abstract class MenuNode {
    public String title;
    protected final IConsole console = new Console();
    protected final Scanner scanner = new Scanner(System.in);

    public abstract void execute(ListingMenuNode prevNode);

    // TODO: maybe make it extensible ( so it will be easy to add new additional opts. )
    public void displayAdditionalMenuOptions(int menuLength, boolean hasHistory) {
        int count = 1;

        if(hasHistory) {
            console.println((menuLength + count) + ". Go Back");
            count++;
        }

        console.println((menuLength + count) + ". Exit");
    }

    private boolean isValidMenuChoice(int choice, int menuSize) {
        // TODO: remove hard coded +2 (refer: 'displayAdditionalMenuOptions').
        return choice >= 1 && choice <= menuSize + 2;
    }

    private Optional<Integer> readInt() {
        if (this.scanner.hasNextInt()) {
            return Optional.of(this.scanner.nextInt());
        } else {
            this.scanner.next();
            return Optional.empty();
        }
    }

    private Optional<Integer> readMenuInput() {
        console.print("Choice: ");
        return readInt();
    }

    public int getChoice(int menuSize) {
        Optional<Integer> input = readMenuInput();

        if (input.isPresent() && isValidMenuChoice(input.get(), menuSize)) {
            return input.get() - 1; // to account for 0 based indexing in arrays.
        }
        return -1;
    }

    protected void exitMenu() {
        System.out.println("Exiting...");
        System.exit(0);
    }
}
