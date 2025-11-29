package org.assignment.core.ports.repositories;

import org.assignment.core.domains.models.Course;

import java.util.List;
import java.util.Optional;

public interface ICourseRepository {
    List<Course> findAll();
    Optional<Course> findById(String courseId);
    Optional<Course> findByCode(String courseCode);
    Optional<Course> saveCourse(Course course);
    Optional<Course> updateCourseById(String courseId, Course course);
    Optional<Course> deleteCourse(String courseId);
}