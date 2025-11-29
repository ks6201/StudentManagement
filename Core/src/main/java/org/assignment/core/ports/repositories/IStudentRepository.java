package org.assignment.core.ports.repositories;

import org.assignment.core.domains.models.Student;

import java.util.List;
import java.util.Optional;

public interface IStudentRepository {
    List<Student> findAll();

    Optional<Student> findById(String studentId);

//    <T> Optional<Student> findStudentByProperty(String property, T value);

    Optional<Student> saveStudent(Student student);

    Optional<Student> updateStudentById(String studentId, Student student); // TODO: use patch DTO

    Optional<Student> deleteStudent(String studentId);
}
