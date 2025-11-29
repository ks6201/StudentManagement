package org.assignment.core.domains.services;

import org.assignment.core.domains.models.Course;
import org.assignment.core.domains.models.Enrollment;
import org.assignment.core.domains.models.Student;
import org.assignment.core.ports.repositories.ICourseRepository;
import org.assignment.core.ports.repositories.IEnrollmentRepository;
import org.assignment.core.ports.repositories.IStudentRepository;
import org.assignment.core.ports.services.IEnrollmentService;

import java.time.LocalDate;
import java.util.List;

public class EnrollmentService implements IEnrollmentService {

    private final IEnrollmentRepository enrollmentRepo;
    private final IStudentRepository studentRepo;
    private final ICourseRepository courseRepo;

    public EnrollmentService(
            IEnrollmentRepository enrollmentRepo,
            IStudentRepository studentRepo,
            ICourseRepository courseRepo
    ) {
        this.enrollmentRepo = enrollmentRepo;
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
    }

    public Enrollment enrollStudent(String studentId, String courseCode) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepo.findByCode(courseCode)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Enrollment enrollment = new Enrollment(
                student.getStudentId(),
                course.getCourseId(),
                LocalDate.now()
        );

        return enrollmentRepo.save(enrollment)
                .orElseThrow(() -> new RuntimeException("Failed to enroll student"));
    }

    public Enrollment removeEnrollment(String enrollmentId) {
        return enrollmentRepo.delete(enrollmentId)
                .orElseThrow(() -> new RuntimeException("Failed to remove enrollment"));
    }

    public Enrollment getEnrollment(String enrollmentId) {
        return enrollmentRepo.findById(enrollmentId)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));
    }

    public List<Enrollment> getStudentEnrollments(String studentId) {
        return enrollmentRepo.findByStudentId(studentId);
    }

    public List<Enrollment> getCourseEnrollments(String courseCode) {
        Course course = courseRepo.findByCode(courseCode)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        return enrollmentRepo.findByCourseId(course.getCourseId());
    }

    public Enrollment assignOrUpdateGrade(String enrollmentId, String grade) {
        Enrollment enrollment = enrollmentRepo.findById(enrollmentId)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        enrollment.setGrade(grade);

        return enrollmentRepo.save(enrollment)
                .orElseThrow(() -> new RuntimeException("Failed to assign/update grade"));
    }
}
