package org.assignment.cli.views.Menus.ManageStudent.CommonMenu;

import org.assignment.cli.context.Context;
import org.assignment.cli.libs.console.Console;
import org.assignment.cli.libs.console.InputType;
import org.assignment.cli.libs.menu.IMenu;
import org.assignment.cli.libs.menu.MenuNode;
import org.assignment.cli.libs.menu.TerminalMenuNode;
import org.assignment.core.domains.models.Student;
import org.assignment.core.ports.services.IStudentService;

public class RemoveStudent implements IMenu {
    @Override
    public MenuNode getMenu() {
        return new TerminalMenuNode("Remove Student", (console, scanner) -> {
            try {
                String studentId = Console.getUserInputFor(
                        "Student ID: ",
                        console,
                        scanner,
                        InputType.STRING
                );

                var studentService = Context.resolve(IStudentService.class);

                Student student = studentService.deleteStudentById(studentId);

                console.println("Student removed successfully: ", student.getStudentId());
            } catch (RuntimeException e) {
                console.println("Error removing student: " + e.getMessage());
            }
        });
    }
}
