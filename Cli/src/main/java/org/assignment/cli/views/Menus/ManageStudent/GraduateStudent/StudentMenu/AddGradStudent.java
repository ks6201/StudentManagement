package org.assignment.cli.views.Menus.ManageStudent.GraduateStudent.StudentMenu;

import org.assignment.cli.context.Context;
import org.assignment.cli.libs.console.Console;
import org.assignment.cli.libs.console.IConsole;
import org.assignment.cli.libs.console.InputType;
import org.assignment.cli.libs.menu.IMenu;
import org.assignment.cli.libs.menu.MenuNode;
import org.assignment.core.domains.models.Student;
import org.assignment.core.domains.models.GraduateStudent;
import org.assignment.cli.libs.menu.TerminalMenuNode;
import org.assignment.core.libs.validator.Validator;
import org.assignment.core.ports.services.IStudentService;

import java.util.Scanner;

public class AddGradStudent implements IMenu {
    @Override
    public MenuNode getMenu() {
        return new TerminalMenuNode("Add Student", (console, scanner) -> {
            try {
                GraduateStudent student = readGraduateStudentInput(console, scanner);

                student.validate();

                var studentService = Context.resolve(IStudentService.class);

                Student savedStudent = studentService.addStudent(student);

                console.println("Student added successfully.");
                console.println("Student ID: ", savedStudent.getStudentId());

            } catch (RuntimeException e) {
                console.println("Error adding student: ", e.getMessage());
            }
        });
    }

    private GraduateStudent readGraduateStudentInput(IConsole console, Scanner scanner) {
        String studentId = Console.getUserInputFor(
                "Student Id: ",
                console,
                scanner,
                InputType.STRING
        );

        String name = Console.getUserInputFor(
                "Name: ",
                console,
                scanner,
                InputType.STRING
        );

        String dob = Console.getUserInputFor(
                "DOB (DD/MM/YYYY): ",
                console,
                scanner,
                InputType.STRING
        );

        String phoneNumber = Console.getUserInputFor(
                "Phone Number: ",
                console,
                scanner,
                InputType.STRING
        );

        String thesisTitle = Console.getUserInputFor(
                "Thesis Title: ",
                console,
                scanner,
                InputType.STRING
        );

        String thesisAdvisor = Console.getUserInputFor(
                "Thesis Advisor: ",
                console,
                scanner,
                InputType.STRING
        );

        String researchArea = Console.getUserInputFor(
                "Research Area: ",
                console,
                scanner,
                InputType.STRING
        );

        return new GraduateStudent(
                studentId,
                name,
                dob,
                phoneNumber,
                thesisTitle,
                thesisAdvisor,
                researchArea
        );
    }

}
