package org.assignment.infra.mapper;

import org.assignment.core.domains.models.Enrollment;
import org.assignment.infra.entities.EnrollmentEntity;

public class EnrollmentMapper {

    public static Enrollment toModel(EnrollmentEntity entity) {
        if (entity == null) return null;

        Enrollment enrollment = new Enrollment(
                entity.getStudentId(),
                entity.getCourseId(),
                entity.getEnrolledOn()
        );

        if(entity.getEnrolledOn() != null){
            enrollment.setEnrollmentId(entity.getEnrollmentId());
        }

        enrollment.setGrade(entity.getGrade());
        return enrollment;
    }

    public static EnrollmentEntity toEntity(Enrollment model) {
        EnrollmentEntity entity = new EnrollmentEntity();

        if (model.getEnrollmentId() != null && !model.getEnrollmentId().isBlank()) {
            entity.setEnrollmentId(model.getEnrollmentId());
        }

        entity.setStudentId(model.getStudentId());
        entity.setCourseId(model.getCourseId());
        entity.setEnrolledOn(model.getEnrolledOn());
        entity.setGrade(model.getGrade());

        return entity;
    }
}
