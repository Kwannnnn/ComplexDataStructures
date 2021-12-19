package nl.saxion.cds.db;

import java.util.Collection;

public interface DataObject<T> {
    T get(Long id);
    Collection<T> getAll();
    void save(T t);
}
