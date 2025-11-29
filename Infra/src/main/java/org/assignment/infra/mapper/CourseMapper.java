package org.assignment.infra.mapper;

import org.assignment.core.domains.models.Course;
import org.assignment.infra.entities.CourseEntity;

public class CourseMapper {

    public static Course toModel(CourseEntity entity) {
        if (entity == null) return null;

        Course course = new Course(
                entity.getCourseName(),
                entity.getCourseCode(),
                entity.getCredits(),
                entity.getInstructorName()
        );

        if (entity.getCourseId() != null) {
            course.setCourseId(entity.getCourseId());
        }

        return course;
    }

    public static CourseEntity toEntity(Course model) {
        CourseEntity entity = new CourseEntity();

        if (model.getCourseId() != null && !model.getCourseId().isBlank()) {
            entity.setCourseId(model.getCourseId());
        }

        entity.setCourseName(model.getCourseName());
        entity.setCourseCode(model.getCourseCode());
        entity.setCredits(model.getCredits());
        entity.setInstructorName(model.getInstructorName());

        return entity;
    }

    public static void updateEntity(CourseEntity entity, Course model) {
        if (model.getCourseName() != null) entity.setCourseName(model.getCourseName());
        if (model.getCourseCode() != null) entity.setCourseCode(model.getCourseCode());
        if (model.getCredits() != null) entity.setCredits(model.getCredits());
        if (model.getInstructorName() != null) entity.setInstructorName(model.getInstructorName());
    }
}
