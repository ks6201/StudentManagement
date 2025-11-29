package org.assignment.cli.views.Menus.CourseMenu;

import org.assignment.cli.context.Context;
import org.assignment.cli.exceptions.CourseNotFoundException;
import org.assignment.cli.libs.console.Console;
import org.assignment.cli.libs.console.InputType;
import org.assignment.cli.libs.menu.IMenu;
import org.assignment.cli.libs.menu.MenuNode;
import org.assignment.cli.libs.menu.TerminalMenuNode;
import org.assignment.core.ports.services.ICourseService;

import java.util.Scanner;

public class RemoveCourse implements IMenu {
    @Override
    public MenuNode getMenu() {
        return new TerminalMenuNode("Remove Course", (console, scanner) -> {
            try {
                var courseService = Context.resolve(ICourseService.class);

                String courseIdOrCode = Console.getUserInputFor(
                        "Course ID or Code to remove: ",
                        console,
                        scanner,
                        InputType.STRING
                );

                var removed = courseService.deleteCourseByCourseId(courseIdOrCode)
                        .or(() -> courseService.deleteCourseByCourseCode(courseIdOrCode))
                        .orElseThrow(
                                () -> new CourseNotFoundException(
                                        "Course with (Id or Code) '" + courseIdOrCode + "' not found."
                                )
                        );

                console.println("Course removed successfully: " + removed.getCourseCode());
            } catch (CourseNotFoundException e) {
                console.println(e.getMessage());
            } catch (RuntimeException e) {
                console.println("Error removing course: " + e.getMessage());
            }
        });
    }
}

