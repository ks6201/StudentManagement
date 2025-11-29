package org.assignment.core.ports.services;

import org.assignment.core.domains.models.Enrollment;

import java.util.List;

public interface IEnrollmentService {
    Enrollment enrollStudent(String studentId, String courseCode);
    Enrollment removeEnrollment(String enrollmentId);
    Enrollment getEnrollment(String enrollmentId);
    List<Enrollment> getStudentEnrollments(String studentId);
    List<Enrollment> getCourseEnrollments(String courseCode);
    Enrollment assignOrUpdateGrade(String enrollmentId, String grade);
}
