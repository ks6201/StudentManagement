package org.assignment.cli.views.Menus.CourseMenu;

import org.assignment.cli.context.Context;
import org.assignment.cli.libs.console.Console;
import org.assignment.cli.libs.console.InputType;
import org.assignment.cli.libs.menu.IMenu;
import org.assignment.cli.libs.menu.MenuNode;
import org.assignment.cli.libs.menu.TerminalMenuNode;
import org.assignment.core.domains.models.Course;
import org.assignment.core.ports.services.ICourseService;

import java.util.Scanner;

public class UpdateCourse implements IMenu {
    @Override
    public MenuNode getMenu() {
        return new TerminalMenuNode("Update Course", (console, scanner) -> {
            try {
                var courseService = Context.resolve(ICourseService.class);

                String courseIdOrCode = Console.getUserInputFor(
                        "Course ID or Code to update: ",
                        console,
                        scanner,
                        InputType.STRING
                );

                Course existing = courseService.getById(courseIdOrCode)
                        .or(() -> courseService.getByCode(courseIdOrCode))
                        .orElseThrow(() -> new RuntimeException("Course not found"));

                String name = Console.getUserInputFor(
                        "Course Name [" + existing.getCourseName() + "]: ",
                        console,
                        scanner,
                        InputType.STRING,
                        true
                );

                if (name != null && !name.isEmpty()) existing.setCourseName(name);

                Integer credits = Console.getUserInputFor(
                        "Credits [" + existing.getCredits() + "]: ",
                        console,
                        scanner,
                        InputType.INTEGER,
                        true
                );
                if(credits != null) existing.setCredits(credits);

                String instructor = Console.getUserInputFor(
                        "Instructor Name [" + existing.getInstructorName() + "]: ",
                        console,
                        scanner,
                        InputType.STRING,
                        true
                );

                if (instructor != null && !instructor.isEmpty()) existing.setInstructorName(instructor);

                Course updated = courseService.updateCourse(existing.getCourseId(), existing);
                console.println("Course updated successfully: ", updated.getCourseCode());
            } catch (RuntimeException e) {
                console.println("Error updating course: ", e.getMessage());
            }
        });
    }
}

