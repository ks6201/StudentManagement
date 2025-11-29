package org.assignment.cli.views.Menus.ManageStudent.GraduateStudent.StudentMenu;

import org.assignment.cli.context.Context;
import org.assignment.cli.exceptions.StudentNotFoundException;
import org.assignment.cli.libs.console.Console;
import org.assignment.cli.libs.console.IConsole;
import org.assignment.cli.libs.console.InputType;
import org.assignment.cli.libs.menu.IMenu;
import org.assignment.cli.libs.menu.MenuNode;
import org.assignment.cli.libs.menu.TerminalMenuNode;
import org.assignment.core.domains.models.GraduateStudent;
import org.assignment.core.domains.models.Student;
import org.assignment.core.ports.services.IStudentService;

import java.util.Scanner;

public class UpdateGradStudent implements IMenu {
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

                if (!(existingStudent instanceof GraduateStudent gs)) {
                    throw new RuntimeException("Student is not a GraduateStudent.");
                }

                console.println("Updating student: ", existingStudent.getStudentId(), " (Graduate)");

                this.updateGraduateStudent(console, scanner, existingStudent, gs);

                Student updatedStudent = studentService.updateStudentById(studentId, existingStudent);
                console.println("Student updated successfully: ", updatedStudent.getStudentId());
            } catch (StudentNotFoundException e) {
                console.println(e.getMessage());
            } catch (RuntimeException e) {
                console.println("Error updating student: ", e.getMessage());
            }
        });
    }

    private void updateGraduateStudent(
            IConsole console,
            Scanner scanner,
            Student existing,
            GraduateStudent gs
    ) {
        String name = Console.getUserInputFor(
                "Name [" + existing.getName() + "]: ",
                console,
                scanner,
                InputType.STRING,
                true
        );
        if (name != null) existing.setName(name);

        String dob = Console.getUserInputFor(
                "DOB [" + existing.getDob() + "]: ",
                console,
                scanner,
                InputType.STRING,
                true
        );
        if (dob != null) existing.setDob(dob);

        String phone = Console.getUserInputFor(
                "Phone Number [" + existing.getPhoneNumber() + "]: ",
                console,
                scanner,
                InputType.STRING,
                true
        );
        if (phone != null) existing.setPhoneNumber(phone);

        String thesisTitle = Console.getUserInputFor(
                "Thesis Title [" + gs.getThesisTitle() + "]: ",
                console,
                scanner,
                InputType.STRING,
                true
        );
        if (thesisTitle != null) gs.setThesisTitle(thesisTitle);

        String thesisAdvisor = Console.getUserInputFor(
                "Thesis Advisor [" + gs.getThesisAdvisor() + "]: ",
                console,
                scanner,
                InputType.STRING,
                true
        );
        if (thesisAdvisor != null) gs.setThesisAdvisor(thesisAdvisor);

        String researchArea = Console.getUserInputFor(
                "Research Area [" + gs.getResearchArea() + "]: ",
                console,
                scanner,
                InputType.STRING,
                true
        );
        if (researchArea != null) gs.setResearchArea(researchArea);
    }
}