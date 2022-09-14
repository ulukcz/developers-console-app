package repository;

import model.Specialty;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public interface GenericRepository<T, ID> {
    void add(T t);
    List<T> getAll();
    T getOne(ID id);
    void update(T oldT, T newT) throws IOException;
    void remove(ID id) throws IOException;
    void save(List<T> t, FileWriter writer);
}
