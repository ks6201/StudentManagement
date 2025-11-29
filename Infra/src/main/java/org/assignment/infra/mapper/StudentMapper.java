package org.assignment.infra.mapper;

import org.assignment.core.domains.models.GraduateStudent;
import org.assignment.core.domains.models.Student;
import org.assignment.infra.entities.StudentEntity;

public class StudentMapper {

    public static StudentEntity toEntity(Student student) {
        StudentEntity entity = new StudentEntity();

        entity.setStudentId(student.getStudentId());
        entity.setName(student.getName());
        entity.setDob(student.getDob());
        entity.setPhoneNumber(student.getPhoneNumber());

        if (student instanceof GraduateStudent gs) {
            entity.setIsGraduated(true);
            entity.setThesisTitle(gs.getThesisTitle());
            entity.setThesisAdvisor(gs.getThesisAdvisor());
            entity.setResearchArea(gs.getResearchArea());
        } else {
            entity.setIsGraduated(false);
        }

        return entity;
    }

    public static Student toModel(StudentEntity entity) {
        if (entity == null) return null;

        if (entity.getIsGraduated()) {
            var student = new GraduateStudent(
                    entity.getStudentId(),
                    entity.getName(),
                    entity.getDob(),
                    entity.getPhoneNumber(),
                    entity.getThesisTitle(),
                    entity.getThesisAdvisor(),
                    entity.getResearchArea()
            );
            student.setStudentId(entity.getStudentId());
            return student;
        }

        var student = new Student(
                entity.getStudentId(),
                entity.getName(),
                entity.getDob(),
                entity.getPhoneNumber()
        );
        if (entity.getStudentId() != null) {
            student.setStudentId(entity.getStudentId());
        }
        return student;
    }

    public static void updateEntity(StudentEntity entity, Student student) {
        if (student.getName() != null) entity.setName(student.getName());
        if (student.getDob() != null) entity.setDob(student.getDob());
        if (student.getPhoneNumber() != null) entity.setPhoneNumber(student.getPhoneNumber());

        if (student instanceof GraduateStudent gs) {
            if (gs.getThesisTitle() != null) entity.setThesisTitle(gs.getThesisTitle());
            if (gs.getThesisAdvisor() != null) entity.setThesisAdvisor(gs.getThesisAdvisor());
            if (gs.getResearchArea() != null) entity.setResearchArea(gs.getResearchArea());
            entity.setIsGraduated(true);
        }
    }
}