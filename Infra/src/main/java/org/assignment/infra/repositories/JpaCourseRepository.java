package org.assignment.infra.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.assignment.core.domains.models.Course;
import org.assignment.core.ports.repositories.ICourseRepository;
import org.assignment.infra.entities.CourseEntity;
import org.assignment.infra.mapper.CourseMapper;

import java.util.List;
import java.util.Optional;

public class JpaCourseRepository implements ICourseRepository {

    private final EntityManagerFactory emf;

    public JpaCourseRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public List<Course> findAll() {
        try (EntityManager em = emf.createEntityManager()) {
            List<CourseEntity> results = em.createQuery(
                    "SELECT c FROM CourseEntity c", CourseEntity.class
            ).getResultList();
            return results.stream().map(CourseMapper::toModel).toList();
        }
    }

    @Override
    public Optional<Course> findById(String courseId) {
        try (EntityManager em = emf.createEntityManager()) {

            CourseEntity entity = em.find(CourseEntity.class, courseId);
            return entity == null ? Optional.empty() : Optional.of(CourseMapper.toModel(entity));
        }
    }

    @Override
    public Optional<Course> findByCode(String courseCode) {
        try (EntityManager em = emf.createEntityManager()) {
            List<CourseEntity> results = em.createQuery(
                            "SELECT c FROM CourseEntity c WHERE c.courseCode = :code",
                            CourseEntity.class
                    )
                    .setParameter("code", courseCode)
                    .getResultList();

            if (results.isEmpty()) return Optional.empty();
            return Optional.of(CourseMapper.toModel(results.get(0)));
        }
    }

    @Override
    public Optional<Course> saveCourse(Course course) {
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();

                CourseEntity entity = CourseMapper.toEntity(course);

                em.merge(entity);

                tx.commit();
                return Optional.of(CourseMapper.toModel(entity));
            } catch (Exception e) {
                if (tx.isActive()) tx.rollback();
                return Optional.empty();
            }
        }
    }

    @Override
    public Optional<Course> updateCourseById(String courseId, Course course) {
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();

                CourseEntity entity = em.find(CourseEntity.class, courseId);
                if (entity == null) {
                    tx.rollback();
                    return Optional.empty();
                }

                CourseMapper.updateEntity(entity, course);
                em.merge(entity);

                tx.commit();
                return Optional.of(CourseMapper.toModel(entity));
            } catch (Exception e) {
                if (tx.isActive()) tx.rollback();
                return Optional.empty();
            }
        }
    }

    @Override
    public Optional<Course> deleteCourse(String courseId) {
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();

                CourseEntity entity = em.find(CourseEntity.class, courseId);
                if (entity == null) {
                    tx.rollback();
                    return Optional.empty();
                }

                em.remove(entity);
                tx.commit();
                return Optional.of(CourseMapper.toModel(entity));
            } catch (Exception e) {
                if (tx.isActive()) tx.rollback();
                return Optional.empty();
            }
        }
    }
}
