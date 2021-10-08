package service;

import java.util.List;

public interface IGeneralService<T> {
    boolean save(T t);

    boolean edit(int id, T t);

    boolean delete(int id);

    List<T> getAll();
}
