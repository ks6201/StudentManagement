package org.assignment.cli.views.Menus.ManageStudent.Student.StudentMenu;

import org.assignment.cli.context.Context;
import org.assignment.cli.exceptions.StudentNotFoundException;
import org.assignment.cli.libs.console.Console;
import org.assignment.cli.libs.console.InputType;
import org.assignment.cli.libs.menu.IMenu;
import org.assignment.cli.libs.menu.MenuNode;
import org.assignment.cli.libs.menu.TerminalMenuNode;
import org.assignment.core.domains.models.GraduateStudent;
import org.assignment.core.domains.models.Student;
import org.assignment.core.ports.services.IStudentService;

public class ViewStudent implements IMenu {
    @Override
    public MenuNode getMenu() {
        return new TerminalMenuNode("View Student", (console, scanner) -> {
            try {
                String studentId = Console.getUserInputFor(
                        "Student ID: ",
                        console,
                        scanner,
                        InputType.STRING
                );

                var studentService = Context.resolve(IStudentService.class);

                Student student = studentService.getStudentById(studentId)
                        .orElseThrow(
                                () -> new StudentNotFoundException(
                                        "Student having id " + studentId + " does not exist"
                                )
                        );

                if (student instanceof GraduateStudent) {
                    return;
                }

                console.println("Student Details:");
                console.println("--------------------------------");
                console.println("Student ID: ", student.getStudentId());
                console.println("Name: ", student.getName());
                console.println("DOB: ", student.getDob());
                console.println("Phone Number: ", student.getPhoneNumber());
                console.println("Type: ", "Undergraduate");

            } catch (StudentNotFoundException e) {
                console.println(e.getMessage());
            } catch (RuntimeException e) {
                console.println("Error: ", e.getMessage());
            }
        });
    }
}
