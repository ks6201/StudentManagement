package org.assignment.cli.views.Menus.ManageStudent.Student.StudentMenu;

import org.assignment.cli.libs.menu.IMenu;
import org.assignment.cli.libs.menu.ListingMenuNode;
import org.assignment.cli.libs.menu.MenuNode;

public class StudentMenu implements IMenu {
    public MenuNode getMenu() {
        var studentMenu = new ListingMenuNode("Manage Students");

        var addStudent = new AddStudent();
        studentMenu.addSubMenu(addStudent.getMenu());

        return studentMenu;
    }
}
