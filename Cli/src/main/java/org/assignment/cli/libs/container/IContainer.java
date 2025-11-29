package org.assignment.cli.libs.container;

public interface IContainer {
    <T> T resolve(Class<T> clazz);
}
