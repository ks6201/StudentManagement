package org.assignment.cli.views.Menus.EnrollmentMenu;

import org.assignment.cli.context.Context;
import org.assignment.cli.exceptions.CourseNotFoundException;
import org.assignment.cli.exceptions.EnrollmentNotFoundException;
import org.assignment.cli.libs.console.Console;
import org.assignment.cli.libs.console.InputType;
import org.assignment.cli.libs.menu.IMenu;
import org.assignment.cli.libs.menu.MenuNode;
import org.assignment.cli.libs.menu.TerminalMenuNode;
import org.assignment.core.libs.DateUtil;
import org.assignment.core.ports.services.ICourseService;
import org.assignment.core.ports.services.IEnrollmentService;

public class ViewEnrollment implements IMenu {
    @Override
    public MenuNode getMenu() {
        return new TerminalMenuNode("View Enrollment", (console, scanner) -> {
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

                var courseService = Context.resolve(ICourseService.class);
                var course = courseService.getByCode(courseCode)
                        .orElseThrow(
                                () -> new CourseNotFoundException(
                                        "Course with code '" + courseCode + "' not found."
                                )
                        );

                var enrollments = enrollmentService.getStudentEnrollments(studentId);
                var enrollment = enrollments.stream()
                        .filter(e -> e.getCourseId().equals(course.getCourseId()))
                        .findFirst()
                        .orElseThrow(
                                () -> new EnrollmentNotFoundException(
                                        "Enrollment for Course Code " +  courseCode + " not found"
                                )
                        );

                console.println("Enrollment ID: ", enrollment.getEnrollmentId());
                console.println("Student ID: ", enrollment.getStudentId());
                console.println("Course ID: ", enrollment.getCourseId());
                console.println("Enrolled On: ", DateUtil.toHuman(enrollment.getEnrolledOn()));
                console.println("Grade: ", (enrollment.getGrade() != null ? enrollment.getGrade() : "Not assigned"));
            } catch (EnrollmentNotFoundException| CourseNotFoundException e) {
                console.println(e.getMessage());
            } catch (Exception e) {
                console.println("Error: " + e.getMessage());
            }
        });
    }
}
