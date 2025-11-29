package org.assignment.core.ports.services;

import org.assignment.core.domains.models.Course;

import java.util.List;
import java.util.Optional;

public interface ICourseService {
    List<Course> fetchAll();
    Optional<Course> getById(String courseId);
    Optional<Course> getByCode(String courseCode);
    Course addCourse(Course course);
    Course updateCourse(String courseId, Course course);
    Optional<Course> deleteCourseByCourseId(String courseId);
    Optional<Course> deleteCourseByCourseCode(String courseCode);
}
