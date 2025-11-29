package org.assignment.cli.views;

import org.assignment.cli.libs.menu.ListingMenuNode;
import org.assignment.cli.libs.menu.MenuNode;

public class MenuExecNode extends MenuNode {
    // A quick check to see whether the root node already ran and the header was shown.
    public static boolean rootNodeExecuted = false;

    private final MenuNode mainMenu;
    private final static int borderLength = 80;

    public MenuExecNode(MenuNode node) {
        this.mainMenu = node;
    }

    public void displayHeader() {
        this.console.clearScreen();
        this.console.println("=".repeat(borderLength));
        this.console.println(
                "\t".repeat(5),
                "Student Management System"
        );
        this.console.println("=".repeat(borderLength));
    }

    @Override
    public void execute(ListingMenuNode prevNode) {
        this.displayHeader();
        this.mainMenu.execute(prevNode);
    }
}