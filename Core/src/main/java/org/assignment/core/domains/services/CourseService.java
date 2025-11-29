package org.assignment.core.domains.services;

import org.assignment.core.domains.models.Course;
import org.assignment.core.ports.repositories.ICourseRepository;
import org.assignment.core.ports.services.ICourseService;

import java.util.List;
import java.util.Optional;

public class CourseService implements ICourseService {

    private final ICourseRepository repo;

    public CourseService(ICourseRepository repo) {
        this.repo = repo;
    }

    public List<Course> fetchAll() {
        return repo.findAll();
    }

    public Optional<Course> getById(String courseId) {
        return repo.findById(courseId);
    }

    public Optional<Course> getByCode(String courseCode) {
        return repo.findByCode(courseCode);
    }

    public Course addCourse(Course course) {
        return repo.saveCourse(course)
                .orElseThrow(() -> new RuntimeException("Failed to create course."));
    }

    public Course updateCourse(String courseId, Course course) {
        return repo.updateCourseById(courseId, course)
                .orElseThrow(() -> new RuntimeException("Failed to update course."));
    }

    @Override
    public Optional<Course> deleteCourseByCourseCode(String courseCode) {
        var code = repo.findByCode(courseCode)
                .orElseThrow(() -> new RuntimeException("Failed to delete course.")
        );
        return this.deleteCourseByCourseId(code.getCourseId());
    }

    public Optional<Course> deleteCourseByCourseId(String courseId) {
        return repo.deleteCourse(courseId);
    }
}