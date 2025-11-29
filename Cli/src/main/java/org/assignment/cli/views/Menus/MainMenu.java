package org.assignment.cli.views.Menus;

import org.assignment.cli.libs.menu.IMenu;
import org.assignment.cli.libs.menu.ListingMenuNode;
import org.assignment.cli.libs.menu.MenuNode;
import org.assignment.cli.views.Menus.CourseMenu.CourseMenu;
import org.assignment.cli.views.Menus.EnrollmentMenu.EnrollmentMenu;
import org.assignment.cli.views.Menus.ManageStudent.ManageStudentMenu;

public class MainMenu implements IMenu {

    public ListingMenuNode getMenu() {
        ListingMenuNode mainMenu = new ListingMenuNode("Root");

        CourseMenu courseMenu = new CourseMenu();
        MenuNode menuNode = courseMenu.getMenu();
        mainMenu.addSubMenu(menuNode);

        ManageStudentMenu manageStudentMenu = new ManageStudentMenu();
        menuNode = manageStudentMenu.getMenu();
        mainMenu.addSubMenu(menuNode);

        EnrollmentMenu  enrollmentMenu = new EnrollmentMenu();
        menuNode = enrollmentMenu.getMenu();
        mainMenu.addSubMenu(menuNode);

        return mainMenu;
    }
}
