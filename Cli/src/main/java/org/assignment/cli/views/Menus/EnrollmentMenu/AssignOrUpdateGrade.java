package org.assignment.cli.views.Menus.EnrollmentMenu;

import org.assignment.cli.context.Context;
import org.assignment.cli.exceptions.CourseNotFoundException;
import org.assignment.cli.exceptions.EnrollmentNotFoundException;
import org.assignment.cli.libs.console.Console;
import org.assignment.cli.libs.console.InputType;
import org.assignment.cli.libs.menu.IMenu;
import org.assignment.cli.libs.menu.MenuNode;
import org.assignment.cli.libs.menu.TerminalMenuNode;
import org.assignment.core.domains.models.Enrollment;
import org.assignment.core.ports.services.ICourseService;
import org.assignment.core.ports.services.IEnrollmentService;

import java.util.List;

public class AssignOrUpdateGrade implements IMenu {
    @Override
    public MenuNode getMenu() {
        return new TerminalMenuNode("Assign / Update Grade", (console, scanner) -> {
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

                String grade = Console.getUserInputFor(
                        "Grade: ",
                        console,
                        scanner,
                        InputType.STRING
                );

                var enrollmentService = Context.resolve(IEnrollmentService.class);
                var courseService = Context.resolve(ICourseService.class);

                var course = courseService.getByCode(courseCode)
                        .orElseThrow(
                                () -> new CourseNotFoundException(
                                        "Course with code '" + courseCode + "' not found."
                                )
                        );

                List<Enrollment> enrollments = enrollmentService.getStudentEnrollments(studentId);
                var enrollment = enrollments.stream()
                        .filter(e -> e.getCourseId().equals(course.getCourseId()))
                        .findFirst()
                        .orElseThrow(
                                () -> new EnrollmentNotFoundException(
                                        "Enrollment for Course Code " +  courseCode + " not found"
                                )
                        );

                enrollment.setGrade(grade.toUpperCase());
                enrollment.validate();
                enrollmentService.assignOrUpdateGrade(enrollment.getEnrollmentId(), grade);

                console.println("Grade updated.");

            } catch (CourseNotFoundException | EnrollmentNotFoundException e) {
                console.println(e.getMessage());
            } catch (Exception e) {
                console.println("Error: ", e.getMessage());
            }
        });
    }
}
