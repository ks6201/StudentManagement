package org.assignment.cli.views.Menus.ManageStudent.Student;

import org.assignment.cli.libs.menu.IMenu;
import org.assignment.cli.libs.menu.ListingMenuNode;
import org.assignment.cli.libs.menu.MenuNode;
import org.assignment.cli.views.Menus.ManageStudent.Student.StudentMenu.AddStudent;
import org.assignment.cli.views.Menus.ManageStudent.CommonMenu.RemoveStudent;
import org.assignment.cli.views.Menus.ManageStudent.Student.StudentMenu.UpdateStudent;
import org.assignment.cli.views.Menus.ManageStudent.Student.StudentMenu.ViewStudent;

public class UnderGraduateStudentMenu implements IMenu {
    @Override
    public MenuNode getMenu() {
        var ugsNode = new ListingMenuNode("Undergraduate Student");

        var addStudent = new AddStudent();
        var removeStudent = new RemoveStudent();
        var updateStudent = new UpdateStudent();
        var viewStudent = new ViewStudent();

        ugsNode.addSubMenu(viewStudent.getMenu());
        ugsNode.addSubMenu(addStudent.getMenu());
        ugsNode.addSubMenu(updateStudent.getMenu());
        ugsNode.addSubMenu(removeStudent.getMenu());

        return ugsNode;
    }
}
