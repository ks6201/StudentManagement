package org.assignment.core.ports.repositories;

import org.assignment.core.domains.models.Enrollment;

import java.util.List;
import java.util.Optional;

public interface IEnrollmentRepository {

    List<Enrollment> findAll();

    List<Enrollment> findByStudentId(String studentId);

    List<Enrollment> findByCourseId(String courseId);

    Optional<Enrollment> findById(String enrollmentId);

    Optional<Enrollment> save(Enrollment enrollment);

    Optional<Enrollment> delete(String enrollmentId);
}

