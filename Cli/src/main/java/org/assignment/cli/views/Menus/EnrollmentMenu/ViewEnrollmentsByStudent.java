package org.assignment.cli.views.Menus.EnrollmentMenu;

import org.assignment.cli.context.Context;
import org.assignment.cli.libs.console.Console;
import org.assignment.cli.libs.console.IConsole;
import org.assignment.cli.libs.console.InputType;
import org.assignment.cli.libs.menu.IMenu;
import org.assignment.cli.libs.menu.MenuNode;
import org.assignment.cli.libs.menu.TerminalMenuNode;
import org.assignment.core.domains.models.Enrollment;
import org.assignment.core.libs.DateUtil;
import org.assignment.core.ports.services.IEnrollmentService;

import java.util.List;

public class ViewEnrollmentsByStudent implements IMenu {
    @Override
    public MenuNode getMenu() {
        return new TerminalMenuNode("View Enrollments by Student", (console, scanner) -> {
            try {
                String studentId = Console.getUserInputFor(
                        "Student ID: ",
                        console,
                        scanner,
                        InputType.STRING
                );

                var enrollmentService = Context.resolve(IEnrollmentService.class);

                List<Enrollment> enrollments = enrollmentService.getStudentEnrollments(studentId);
                if (enrollments.isEmpty()) {
                    console.println("No enrollments found for student " + studentId);
                    return;
                }

                console.println("Enrollments for student ", studentId, ":");
                displayEnrollments(console, enrollments);
            } catch (Exception e) {
                console.println("Error: " + e.getMessage());
            }
        });
    }

    public static void displayEnrollments(IConsole console, List<Enrollment> enrollments) {
        for (Enrollment e : enrollments) {
            console.println("-".repeat(32));

            console.println(
                    "Enrollment ID: ",
                    e.getEnrollmentId()
            );
            console.println(
                    "Student ID: ",
                    e.getStudentId()
            );
            console.println(
                    "Enrolled On: ",
                    DateUtil.toHuman(e.getEnrolledOn())
            );
            console.println(
                    "Grade: ",
                    (e.getGrade() != null ? e.getGrade() : "Not assigned")
            );
        }
    }
}

