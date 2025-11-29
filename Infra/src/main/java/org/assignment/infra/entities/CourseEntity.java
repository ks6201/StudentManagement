package org.assignment.infra.entities;

import java.util.UUID;
import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class CourseEntity {

    @Id
    @Column(name = "course_id", nullable = false, updatable = false)
    private String courseId = UUID.randomUUID().toString();

    @Column(name = "course_name", nullable = false)
    private String courseName;

    @Column(name = "course_code", nullable = false, unique = true)
    private String courseCode;

    @Column(name = "credits", nullable = false)
    private Integer credits;

    @Column(name = "instructor_name")
    private String instructorName;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String name) {
        this.courseName = name;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String code) {
        this.courseCode = code;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }
}
