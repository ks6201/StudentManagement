package org.assignment.cli.views.Menus.EnrollmentMenu;

import org.assignment.cli.context.Context;
import org.assignment.cli.libs.console.Console;
import org.assignment.cli.libs.console.InputType;
import org.assignment.cli.libs.menu.IMenu;
import org.assignment.cli.libs.menu.MenuNode;
import org.assignment.cli.libs.menu.TerminalMenuNode;
import org.assignment.core.ports.services.IEnrollmentService;

public class EnrollStudent implements IMenu {
    @Override
    public MenuNode getMenu() {
        return new TerminalMenuNode("Enroll a Student", (console, scanner) -> {
            console.println("Enroll a student into a course:");

            try {
                String studentId = Console.getUserInputFor(
                        "Student ID: ",
                        console,
                        scanner,
                        InputType.STRING
                );

                String courseCode = Console.getUserInputFor(
                        "Course Code: ",
                        console,
                        scanner,
                        InputType.STRING
                );

                var enrollmentService = Context.resolve(IEnrollmentService.class);

                var enrollment = enrollmentService.enrollStudent(studentId, courseCode);

                console.println("Enrollment completed.");
                console.println("Enrollment ID: ", enrollment.getEnrollmentId());
            } catch (Exception e) {
                console.println("Error: " + e.getMessage());
            }
        });
    }
}
