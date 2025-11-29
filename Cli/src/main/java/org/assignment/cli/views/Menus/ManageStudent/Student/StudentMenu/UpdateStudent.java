package org.assignment.cli.views.Menus.ManageStudent.Student.StudentMenu;

import org.assignment.cli.context.Context;
import org.assignment.cli.exceptions.StudentNotFoundException;
import org.assignment.cli.libs.console.Console;
import org.assignment.cli.libs.console.InputType;
import org.assignment.cli.libs.menu.IMenu;
import org.assignment.cli.libs.menu.MenuNode;
import org.assignment.cli.libs.menu.TerminalMenuNode;
import org.assignment.core.domains.models.GraduateStudent;
import org.assignment.core.domains.models.Student;
import org.assignment.core.ports.services.IStudentService;

public class UpdateStudent implements IMenu {
    @Override
    public MenuNode getMenu() {
        return new TerminalMenuNode("Update Student", (console, scanner) -> {
            try {
                String studentId = Console.getUserInputFor(
                        "Student ID: ",
                        console,
                        scanner,
                        InputType.STRING
                );

                var studentService = Context.resolve(IStudentService.class);

                Student existingStudent = studentService.getStudentById(studentId)
                        .orElseThrow(
                                () -> new StudentNotFoundException(
                                        "Student having id " + studentId + " does not exist"
                                )
                        );

                if (existingStudent instanceof GraduateStudent) {
                    return;
                }

                console.println("Updating student: ", existingStudent.getStudentId(), " (Graduate)");

                String name = Console.getUserInputFor(
                        "Name [" + existingStudent.getName() + "]: ",
                        console,
                        scanner,
                        InputType.STRING,
                        true
                );
                if (name != null) existingStudent.setName(name);

                String dob = Console.getUserInputFor(
                        "DOB [" + existingStudent.getDob() + "]: ",
                        console,
                        scanner,
                        InputType.STRING,
                        true
                );
                if (dob != null) existingStudent.setDob(dob);

                String phone = Console.getUserInputFor(
                        "Phone Number [" + existingStudent.getPhoneNumber() + "]: ",
                        console,
                        scanner,
                        InputType.STRING,
                        true
                );
                if (phone != null) existingStudent.setPhoneNumber(phone);

                Student updatedStudent = studentService.updateStudentById(studentId, existingStudent);
                console.println("Student updated successfully: ", updatedStudent.getStudentId());
            } catch (StudentNotFoundException e) {
                console.println(e.getMessage());
            } catch (RuntimeException e) {
                console.println("Error updating student: ", e.getMessage());
            }
        });
    }
}
