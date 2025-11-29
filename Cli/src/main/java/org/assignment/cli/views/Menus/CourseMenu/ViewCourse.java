package org.assignment.cli.views.Menus.CourseMenu;

import org.assignment.cli.context.Context;
import org.assignment.cli.exceptions.CourseNotFoundException;
import org.assignment.cli.libs.console.Console;
import org.assignment.cli.libs.console.InputType;
import org.assignment.cli.libs.menu.IMenu;
import org.assignment.cli.libs.menu.MenuNode;
import org.assignment.cli.libs.menu.TerminalMenuNode;
import org.assignment.core.domains.models.Course;
import org.assignment.core.ports.services.ICourseService;

import java.util.Scanner;

public class ViewCourse implements IMenu {
    @Override
    public MenuNode getMenu() {
        return new TerminalMenuNode("View Course", (console, scanner) -> {
            try {
                var courseService = Context.resolve(ICourseService.class);

                String courseCodeOrId = Console.getUserInputFor(
                        "Course Id or Code: ",
                        console,
                        scanner,
                        InputType.STRING
                );

                Course course = courseService.getById(courseCodeOrId)
                        .or(() -> courseService.getByCode(courseCodeOrId))
                        .orElseThrow(
                                () -> new CourseNotFoundException(
                                        "Course with Id or Code '" + courseCodeOrId + "' not found."
                                )
                        );

                console.println("Course Details:");
                console.println("--------------------------------");
                console.println("Course ID: ", course.getCourseId());
                console.println("Course Name: ", course.getCourseName());
                console.println("Course Code: ", course.getCourseCode());
                console.println("Credits: ", Integer.toString(course.getCredits()));
                console.println("Instructor: ", (course.getInstructorName() != null ? course.getInstructorName() : "N/A"));

            } catch (CourseNotFoundException e) {
                console.println(e.getMessage());
            } catch (RuntimeException e) {
                console.println("Error viewing course: ", e.getMessage());
            }
        });
    }
}

