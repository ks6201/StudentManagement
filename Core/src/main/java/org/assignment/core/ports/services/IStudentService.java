package org.assignment.core.ports.services;

import org.assignment.core.domains.models.Student;

import java.util.List;
import java.util.Optional;

public interface IStudentService {
    List<Student> fetchStudents();
    Optional<Student> getStudentById(String studentId);
    Student addStudent(Student student);
    Student updateStudentById(
            String studentId,
            Student student
    );
    Student deleteStudentById(String studentId);
}
