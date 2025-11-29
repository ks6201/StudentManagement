package org.assignment.cli;

import org.assignment.cli.context.Context;
import org.assignment.cli.views.MenuExecNode;
import org.assignment.cli.views.Menus.MainMenu;

public class Main {

    public static void main(String[] args) {
        Context.initialize();

        MainMenu mainMenu = new MainMenu();
        MenuExecNode vm = new MenuExecNode(
            mainMenu.getMenu()
        );

        vm.execute(null);
    }
}
