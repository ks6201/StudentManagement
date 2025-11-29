package org.assignment.infra.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.assignment.core.domains.models.Student;
import org.assignment.core.ports.repositories.IStudentRepository;
import org.assignment.infra.mapper.StudentMapper;
import org.assignment.infra.entities.StudentEntity;

import java.util.List;
import java.util.Optional;

public class JpaStudentRepository implements IStudentRepository {

    private final EntityManagerFactory emf;

    public JpaStudentRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public List<Student> findAll() {
        try (EntityManager em = emf.createEntityManager()) {

            List<StudentEntity> list = em.createQuery(
                    "SELECT s FROM StudentEntity s",
                    StudentEntity.class
            ).getResultList();

            return list.stream()
                    .map(StudentMapper::toModel)
                    .toList();

        } catch (RuntimeException e) {
            return List.of();
        }
    }

    @Override
    public Optional<Student> findById(String id) {
        try (EntityManager em = emf.createEntityManager()) {

            StudentEntity entity = em.find(StudentEntity.class, id);
            if (entity == null) return Optional.empty();

            return Optional.of(StudentMapper.toModel(entity));

        } catch (RuntimeException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Student> saveStudent(Student student) {
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction tx = em.getTransaction();

            try {
                tx.begin();

                StudentEntity entity = StudentMapper.toEntity(student);
                em.merge(entity);

                tx.commit();

                return Optional.of(StudentMapper.toModel(entity));

            } catch (RuntimeException e) {
                if (tx.isActive()) tx.rollback();
                return Optional.empty();
            }
        }
    }

    @Override
    public Optional<Student> updateStudentById(String studentId, Student updated) {
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction tx = em.getTransaction();

            try {
                tx.begin();

                StudentEntity entity = em.find(StudentEntity.class, studentId);
                if (entity == null) {
                    tx.rollback();
                    return Optional.empty();
                }

                StudentMapper.updateEntity(entity, updated);
                em.merge(entity);

                tx.commit();

                return Optional.of(StudentMapper.toModel(entity));

            } catch (RuntimeException e) {
                if (tx.isActive()) tx.rollback();
                return Optional.empty();
            }
        }
    }

    @Override
    public Optional<Student> deleteStudent(String studentId) {
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction tx = em.getTransaction();

            try {
                tx.begin();

                StudentEntity entity = em.find(StudentEntity.class, studentId);
                if (entity == null) {
                    tx.rollback();
                    return Optional.empty();
                }

                em.remove(entity);
                tx.commit();

                return Optional.of(StudentMapper.toModel(entity));

            } catch (RuntimeException e) {
                if (tx.isActive()) tx.rollback();
                return Optional.empty();
            }
        }
    }
}