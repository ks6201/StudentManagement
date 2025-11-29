package org.assignment.core.domains.models;

import org.assignment.core.libs.validator.Pattern.Pattern;
import org.assignment.core.libs.validator.Required.Required;
import org.assignment.core.libs.validator.Validator;

import java.time.LocalDate;

public class Enrollment {

    private String enrollmentId;

    @Required
    @Pattern(value = "[A-D][+-]?|F")
    private String grade;

    @Required
    private String studentId;

    @Required
    private String courseId;

    @Required
    private LocalDate enrolledOn;

    public Enrollment(
            String studentId,
            String courseId,
            LocalDate enrolledOn
    ) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrolledOn = enrolledOn;
    }

    public void validate() {
        Validator.validate(this);
    }

    public String getEnrollmentId() {
        return enrollmentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public LocalDate getEnrolledOn() {
        return enrolledOn;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setEnrollmentId(String enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public void setEnrolledOn(LocalDate enrolledOn) {
        this.enrolledOn = enrolledOn;
    }
}

