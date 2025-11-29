package org.assignment.infra.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.assignment.core.domains.models.Enrollment;
import org.assignment.core.ports.repositories.IEnrollmentRepository;
import org.assignment.infra.entities.EnrollmentEntity;
import org.assignment.infra.mapper.EnrollmentMapper;

import java.util.List;
import java.util.Optional;

public class JpaEnrollmentRepository implements IEnrollmentRepository {

    private final EntityManagerFactory emf;

    public JpaEnrollmentRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public List<Enrollment> findAll() {
        try (EntityManager em = emf.createEntityManager()) {
            List<EnrollmentEntity> results = em.createQuery(
                    "SELECT e FROM EnrollmentEntity e",
                    EnrollmentEntity.class
            ).getResultList();

            return results.stream()
                    .map(EnrollmentMapper::toModel)
                    .toList();
        }
    }

    @Override
    public List<Enrollment> findByStudentId(String studentId) {
        try (EntityManager em = emf.createEntityManager()) {

            List<EnrollmentEntity> results = em.createQuery(
                            "SELECT e FROM EnrollmentEntity e WHERE e.studentId = :sid",
                            EnrollmentEntity.class
                    )
                    .setParameter("sid", studentId)
                    .getResultList();

            return results.stream()
                    .map(EnrollmentMapper::toModel)
                    .toList();
        }
    }

    @Override
    public List<Enrollment> findByCourseId(String courseId) {
        try (EntityManager em = emf.createEntityManager()) {

            List<EnrollmentEntity> results = em.createQuery(
                            "SELECT e FROM EnrollmentEntity e WHERE e.courseId = :cid",
                            EnrollmentEntity.class
                    )
                    .setParameter("cid", courseId)
                    .getResultList();

            return results.stream()
                    .map(EnrollmentMapper::toModel)
                    .toList();
        }
    }

    @Override
    public Optional<Enrollment> findById(String enrollmentId) {
        try (EntityManager em = emf.createEntityManager()) {
            EnrollmentEntity entity = em.find(EnrollmentEntity.class, enrollmentId);
            return entity == null ? Optional.empty() : Optional.of(EnrollmentMapper.toModel(entity));
        }
    }

    @Override
    public Optional<Enrollment> save(Enrollment enrollment) {
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction tx = em.getTransaction();

            try {
                tx.begin();
                EnrollmentEntity entity = EnrollmentMapper.toEntity(enrollment);
                em.merge(entity);
                tx.commit();
                return Optional.of(EnrollmentMapper.toModel(entity));
            } catch (RuntimeException e) {
                if (tx.isActive()) tx.rollback();
                return Optional.empty();
            }
        }
    }

    @Override
    public Optional<Enrollment> delete(String enrollmentId) {
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction tx = em.getTransaction();

            try {
                tx.begin();
                EnrollmentEntity entity = em.find(EnrollmentEntity.class, enrollmentId);
                if (entity == null) {
                    tx.rollback();
                    return Optional.empty();
                }
                em.remove(entity);
                tx.commit();
                return Optional.of(EnrollmentMapper.toModel(entity));
            } catch (RuntimeException e) {
                if (tx.isActive()) tx.rollback();
                return Optional.empty();
            }
        }
    }
}

