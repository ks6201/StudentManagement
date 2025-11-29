package org.assignment.cli.views.Menus.ManageStudent;

import org.assignment.cli.libs.menu.IMenu;
import org.assignment.cli.libs.menu.ListingMenuNode;
import org.assignment.cli.libs.menu.MenuNode;
import org.assignment.cli.views.Menus.ManageStudent.GraduateStudent.GraduateStudentMenu;
import org.assignment.cli.views.Menus.ManageStudent.Student.UnderGraduateStudentMenu;

public class ManageStudentMenu implements IMenu {
    @Override
    public MenuNode getMenu() {
        var manageStudentMenu = new ListingMenuNode("Manage Student");

        var ugMenu = new UnderGraduateStudentMenu();
        var menuNode = ugMenu.getMenu();
        manageStudentMenu.addSubMenu(menuNode);

        var gMenu = new GraduateStudentMenu();
        menuNode = gMenu.getMenu();
        manageStudentMenu.addSubMenu(menuNode);

        return  manageStudentMenu;
    }
}
