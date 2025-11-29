package org.assignment.core.domains.services;

import org.assignment.core.domains.models.Student;
import org.assignment.core.ports.repositories.IStudentRepository;
import org.assignment.core.ports.services.IStudentService;

import java.util.List;
import java.util.Optional;

public class StudentService implements IStudentService {

    private final IStudentRepository studentRepo;

    public StudentService(IStudentRepository studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Override
    public List<Student> fetchStudents() {
        return studentRepo.findAll();
    }

    @Override
    public Optional<Student> getStudentById(String studentId) {
        if (studentId == null || studentId.isBlank()) {
            return Optional.empty();
        }
        return studentRepo.findById(studentId);
    }

    @Override
    public Student addStudent(Student student) throws RuntimeException {
        return studentRepo.saveStudent(student)
                .orElseThrow(() -> new RuntimeException("Failed to create student."));
    }

    @Override
    public Student updateStudentById(String studentId, Student student) {
        return studentRepo.updateStudentById(studentId, student)
                .orElseThrow(() -> new RuntimeException("Failed to update student."));
    }

    @Override
    public Student deleteStudentById(String studentId) {
        return studentRepo.deleteStudent(studentId)
                .orElseThrow(() -> new RuntimeException("Failed to delete student."));
    }
}
