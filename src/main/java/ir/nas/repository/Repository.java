package ir.nas.repository;

import java.util.Optional;
import java.util.Set;

public interface Repository<ID, T>
{
    ID save(T t);

    Optional<T> read(ID id);

    int update(T t);

    Optional<T> delete(ID id);

    Set<T> findAll();
}
