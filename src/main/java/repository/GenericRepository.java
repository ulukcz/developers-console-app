package repository;

import java.util.List;

public interface GenericRepository<T, ID> {
    T save(T t);
    List<T> getAll();
    T getOne(ID id);
    T update(T t);
    void removeById(ID id);
}
