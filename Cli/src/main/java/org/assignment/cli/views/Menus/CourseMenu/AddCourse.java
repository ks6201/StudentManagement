package org.assignment.cli.views.Menus.CourseMenu;


import org.assignment.cli.libs.console.Console;
import org.assignment.cli.libs.console.InputType;
import org.assignment.cli.libs.menu.IMenu;
import org.assignment.cli.libs.menu.MenuNode;
import org.assignment.cli.libs.menu.TerminalMenuNode;
import org.assignment.cli.context.Context;
import org.assignment.core.domains.models.Course;
import org.assignment.core.libs.validator.Validator;
import org.assignment.core.ports.services.ICourseService;

import java.util.Scanner;

public class AddCourse implements IMenu {
    @Override
    public MenuNode getMenu() {
        return new TerminalMenuNode("Add Course", (console, scanner) -> {
            try {
                var courseService = Context.resolve(ICourseService.class);

                String name = Console.getUserInputFor(
                        "Course Name: ",
                        console,
                        scanner,
                        InputType.STRING
                );

                String code = Console.getUserInputFor(
                        "Course Code: ",
                        console,
                        scanner,
                        InputType.STRING
                );

                int credits = Console.getUserInputFor(
                        "Credits (1-5): ",
                        console,
                        scanner,
                        InputType.INTEGER
                );

                String instructor = Console.getUserInputFor(
                        "Instructor Name: ",
                        console,
                        scanner,
                        InputType.STRING
                );

                Course course = new Course(
                        name,
                        code,
                        credits,
                        instructor
                );
                course.validate();

                Course savedCourse = courseService.addCourse(course);

                console.println("Course added successfully: " + savedCourse.getCourseCode());
            } catch (RuntimeException e) {
                console.println("Error adding course: " + e.getMessage());
            }
        });
    }
}