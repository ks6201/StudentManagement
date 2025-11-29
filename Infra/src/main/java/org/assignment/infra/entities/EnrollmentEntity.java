package org.assignment.infra.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "student_course_enrollments")
public class EnrollmentEntity {

    @Id
    @Column(nullable = false, updatable = false)
    private String enrollmentId = UUID.randomUUID().toString();

    @Column(nullable = false)
    private String studentId;

    @Column(nullable = false)
    private String courseId;

    @Column(nullable = false)
    private LocalDate enrolledOn;

    private String grade;

    public String getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(String enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public LocalDate getEnrolledOn() {
        return enrolledOn;
    }

    public void setEnrolledOn(LocalDate enrolledOn) {
        this.enrolledOn = enrolledOn;
    }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    @Override
    public String toString() {
        return """
           {
             "enrollmentId": %s,
             "studentId": %s,
             "courseId": %s,
             "enrolledOn": "%s",
             "grade": %s
           }""".formatted(
                enrollmentId,
                studentId,
                courseId,
                enrolledOn,
                grade == null ? "null" : "\"" + grade + "\""
        ).trim();
    }
}