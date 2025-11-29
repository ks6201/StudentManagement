package org.assignment.cli.libs.container;

public class ContainerEntry {
    public final Object factory;
    public final ContainerTypes containerType;
    ContainerEntry(ContainerTypes containerType, Object factory) {
        this.factory = factory;
        this.containerType = containerType;
    }
}
