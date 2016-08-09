package ua.com.guide.dao;

import java.util.List;

/**
 * Created by Max on 07.08.2016.
 */
public interface AbstractDao<T> {

    T getById(Integer id);

    List<T> findAll();

    T create(T entity);

    T update (T entity);

    void delete (T entity);
}
