package org.assignment.cli.views.Menus.ManageStudent.Student.StudentMenu;

import org.assignment.cli.context.Context;
import org.assignment.cli.libs.console.Console;
import org.assignment.cli.libs.console.InputType;
import org.assignment.cli.libs.menu.IMenu;
import org.assignment.cli.libs.menu.MenuNode;
import org.assignment.cli.libs.menu.TerminalMenuNode;
import org.assignment.core.domains.models.Student;
import org.assignment.core.libs.validator.Validator;
import org.assignment.core.ports.services.IStudentService;

public class AddStudent implements IMenu {
    @Override
    public MenuNode getMenu() {
        return new TerminalMenuNode("Add Student", (console, scanner) -> {
            try {
                IStudentService studentService = Context.resolve(IStudentService.class);

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

                var student = new Student(
                        studentId,
                        name,
                        dob,
                        phoneNumber
                );

                student.validate();

                Student savedStudent = studentService.addStudent(student);
                console.println("Student added successfully.");
                console.println("Student ID: ", savedStudent.getStudentId());

            } catch (RuntimeException e) {
                console.println("Error adding student: " + e.getMessage());
            }
        });
    }
}
