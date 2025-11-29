package org.assignment.cli.context;

import jakarta.persistence.Persistence;
import jakarta.persistence.EntityManagerFactory;
import org.assignment.cli.libs.container.Container;
import org.assignment.core.ports.repositories.ICourseRepository;
import org.assignment.core.ports.repositories.IEnrollmentRepository;
import org.assignment.core.ports.services.ICourseService;
import org.assignment.core.ports.services.IEnrollmentService;
import org.assignment.core.domains.services.CourseService;
import org.assignment.core.domains.services.EnrollmentService;
import org.assignment.core.domains.services.StudentService;
import org.assignment.core.ports.services.IStudentService;
import org.assignment.core.ports.repositories.IStudentRepository;
import org.assignment.infra.repositories.JpaCourseRepository;
import org.assignment.infra.repositories.JpaEnrollmentRepository;
import org.assignment.infra.repositories.JpaStudentRepository;

public class Context {
    public static Container container;

    public static void initialize() {
        container = new Container();
        registerSingletons();
    }

    public static void registerSingletons() {
        container.addSingleton(EntityManagerFactory.class, () ->
                Persistence.createEntityManagerFactory("StudentMgmtPU")
        );

        container.addSingleton(
                IStudentRepository.class,
                () -> new JpaStudentRepository(
                        container.resolve(EntityManagerFactory.class)
                )
        );

        container.addSingleton(
                IStudentService.class,
                () -> new StudentService(
                        container.resolve(IStudentRepository.class)
                )
        );

        container.addSingleton(
                ICourseRepository.class,
                () -> new JpaCourseRepository(
                        container.resolve(EntityManagerFactory.class)
                )
        );

        container.addSingleton(
                ICourseService.class,
                () -> new CourseService(
                        container.resolve(ICourseRepository.class)
                )
        );

        container.addSingleton(
                IEnrollmentRepository.class,
                () -> new JpaEnrollmentRepository(
                        container.resolve(EntityManagerFactory.class)
                )
        );

        container.addSingleton(
                IEnrollmentService.class,
                () -> new EnrollmentService(
                    container.resolve(IEnrollmentRepository.class),
                        container.resolve(IStudentRepository.class),
                        container.resolve(ICourseRepository.class)
                )
        );
    }

    public static <T>  T resolve(Class<T> clazz) throws RuntimeException {
        return container.resolve(clazz);
    }
}
