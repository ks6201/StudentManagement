package org.assignment.cli.views.Menus.EnrollmentMenu;

import org.assignment.cli.context.Context;
import org.assignment.cli.libs.console.Console;
import org.assignment.cli.libs.console.InputType;
import org.assignment.cli.libs.menu.IMenu;
import org.assignment.cli.libs.menu.MenuNode;
import org.assignment.cli.libs.menu.TerminalMenuNode;
import org.assignment.core.domains.models.Enrollment;
import org.assignment.core.ports.services.IEnrollmentService;

import java.util.List;

public class ViewEnrollmentsByCourse implements IMenu {
    @Override
    public MenuNode getMenu() {
        return new TerminalMenuNode("View Enrollments by Course", (console, scanner) -> {
            try {
                String courseCode = Console.getUserInputFor(
                        "Course Code: ",
                        console,
                        scanner,
                        InputType.STRING
                );

                var enrollmentService = Context.resolve(IEnrollmentService.class);

                var enrollments = enrollmentService.getCourseEnrollments(courseCode);
                if (enrollments.isEmpty()) {
                    console.println("No enrollments found for course ", courseCode);
                    return;
                }

                console.println("Enrollments for course ", courseCode, ":");
                ViewEnrollmentsByStudent.displayEnrollments(console, enrollments);
            } catch (Exception e) {
                console.println("Error: " + e.getMessage());
            }
        });
    }
}
