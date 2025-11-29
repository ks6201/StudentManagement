package org.assignment.cli.libs.menu;

import org.assignment.cli.views.MenuExecNode;

import java.util.ArrayList;
import java.util.List;

public class ListingMenuNode extends MenuNode {

    ListingMenuNode lastMenuNode;
    public List<MenuNode> subMenus = new ArrayList<>();

    public ListingMenuNode(String title) {
        this.title = title;
    }

    public void addSubMenu(MenuNode node) {
        this.subMenus.add(node);
    }

    @Override
    public String toString() {
        return "MenuNode{" +
                "title='" + title + '\'' +
                ", subMenus=" + subMenus +
                '}';
    }

    void displayMenu(ListingMenuNode menu) {
        int bound = menu.subMenus.size();
        for (int idx = 0; idx < bound; idx++) {
            console.println(
                    Integer.toString(idx + 1),
                    ". ",
                    menu.subMenus.get(idx).title
            );
        }
    }

    public void listMenu() {
        if(MenuExecNode.rootNodeExecuted) {
            this.console.clearScreen();
        }
        this.displayMenu(this);
        boolean hasHistory = this.lastMenuNode != null;
        this.displayAdditionalMenuOptions(this.subMenus.size(), hasHistory);
        console.println();
        MenuExecNode.rootNodeExecuted = true;
    }

    @Override
    public void execute(ListingMenuNode prevNode) {
        this.lastMenuNode = prevNode;

        int subMenuLen = subMenus.size();
        boolean hasHistory = this.lastMenuNode != null;
        int exitOption = hasHistory ? subMenuLen + 1 : subMenuLen;

        int choice;
        while (true) {

            this.listMenu();
            choice = this.getChoice(subMenuLen);
            if(choice == -1) continue;

            if(choice > subMenuLen - 1) {

                if(hasHistory && choice == subMenuLen) {
                    prevNode.execute(prevNode.lastMenuNode);
                    return;
                } else if (choice == exitOption) {
                    this.exitMenu();
                }
                continue;
            }

            MenuNode node = this.subMenus.get(choice);
            node.execute(this);
            break;
        }
    }
}
