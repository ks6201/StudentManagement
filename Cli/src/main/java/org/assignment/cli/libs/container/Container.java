package org.assignment.cli.libs.container;

import java.util.Map;
import java.util.HashMap;
import java.util.function.Supplier;

public class Container implements IContainer {

    private final Map<Class<?>, ContainerEntry> container = new HashMap<>();

    public void addSingleton(Class<?> clazz,  Supplier<?> factory) {
        this.container.put(
                clazz,
                new ContainerEntry(ContainerTypes.SINGLETON, factory.get())
        );
    }

    public void addTransient(Class<?> clazz, Supplier<?> factory) {
        this.container.put(
                clazz,
                new ContainerEntry(ContainerTypes.TRANSIENT, factory)
        );
    }

    public void addScoped(Class<?> clazz, Supplier<?> factory) {
        this.container.put(
                clazz,
                new ContainerEntry(ContainerTypes.SCOPED, factory)
        );
    }

    @SuppressWarnings("unchecked")
    public <T> T resolve(Class<T> clazz) throws RuntimeException {
        ContainerEntry entry = container.get(clazz);

        if(entry.containerType ==  ContainerTypes.SINGLETON) {
            return (T) entry.factory;
        }
        if(entry.containerType ==  ContainerTypes.TRANSIENT) {
            var supplier = (Supplier<T>) entry.factory;
            return supplier.get();
        }

        throw new RuntimeException("Container type not supported");
    }

    public Scope createScope() {
        final Map<Class<?>, Object> scopeMap = new HashMap<>();

        for(var entry : this.container.entrySet()) {
            var clazz = entry.getKey();
            var value = entry.getValue();
            if(value.containerType !=  ContainerTypes.SCOPED) continue;
            var supplier = (Supplier<?>) value.factory;

            scopeMap.put(clazz, supplier.get());
        }

        return new Scope(scopeMap);
    }


    public static class Scope implements IContainer {
        private final Map<Class<?>, Object> scopeMap;

        public Scope(Map<Class<?>, Object> scopeMap) {
            this.scopeMap = scopeMap;
        }

        @SuppressWarnings("unchecked")
        public <T> T resolve(Class<T> type) {
            return (T) scopeMap.get(type);
        }
    }
}
