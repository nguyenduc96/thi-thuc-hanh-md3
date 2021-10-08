package dao;

import java.util.List;

public interface IGeneralDao<T> {
    boolean save(T t);

    boolean edit(int id, T t);

    boolean delete(int id);

    List<T> getAll();
}
