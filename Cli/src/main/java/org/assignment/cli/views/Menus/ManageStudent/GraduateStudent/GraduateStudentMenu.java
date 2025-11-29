package org.assignment.cli.views.Menus.ManageStudent.GraduateStudent;

import org.assignment.cli.libs.menu.IMenu;
import org.assignment.cli.libs.menu.ListingMenuNode;
import org.assignment.cli.libs.menu.MenuNode;
import org.assignment.cli.views.Menus.ManageStudent.GraduateStudent.StudentMenu.AddGradStudent;
import org.assignment.cli.views.Menus.ManageStudent.CommonMenu.RemoveStudent;
import org.assignment.cli.views.Menus.ManageStudent.GraduateStudent.StudentMenu.UpdateGradStudent;
import org.assignment.cli.views.Menus.ManageStudent.GraduateStudent.StudentMenu.ViewGradStudent;

public class GraduateStudentMenu implements IMenu {
    @Override
    public MenuNode getMenu() {
        var gsNode = new ListingMenuNode("Graduate Student");

        var addStudent = new AddGradStudent();
        var removeStudent = new RemoveStudent();
        var updateStudent = new UpdateGradStudent();
        var viewStudent = new ViewGradStudent();

        gsNode.addSubMenu(viewStudent.getMenu());
        gsNode.addSubMenu(addStudent.getMenu());
        gsNode.addSubMenu(updateStudent.getMenu());
        gsNode.addSubMenu(removeStudent.getMenu());

        return gsNode;
    }
}
