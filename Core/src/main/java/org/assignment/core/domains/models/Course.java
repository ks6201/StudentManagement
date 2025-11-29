package org.assignment.core.domains.models;

import org.assignment.core.libs.validator.Length.Length;
import org.assignment.core.libs.validator.Range.Range;
import org.assignment.core.libs.validator.Required.Required;
import org.assignment.core.libs.validator.Validator;

import java.util.Objects;

public class Course {

    private String courseId;

    @Required
    private String courseName;

    @Required
    @Length(min=1)
    private String courseCode;

    @Range(min = 1, max = 5)
    private Integer credits;

    @Required(errorMessage = "Instructor's name is required")
    private String instructorName;

    public Course(
            String courseName,
            String courseCode,
            Integer credits,
            String instructorName
    ) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.credits = credits;
        this.instructorName = instructorName;
    }

    public void validate() {
        Validator.validate(this);
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String id) {
        this.courseId = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public Integer getCredits() {
        return credits;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) return false;
        Course other = (Course) obj;
        return Objects.equals(courseId, other.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId);
    }
}
