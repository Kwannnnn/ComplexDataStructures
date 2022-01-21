package nl.saxion.cds.db;

import java.util.Collection;
import java.util.Optional;

public interface DataObject<T> {
    Optional<T> get(Long id);
    Collection<T> getAll();
    void save(T t);
}
