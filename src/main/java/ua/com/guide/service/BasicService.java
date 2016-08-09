package ua.com.guide.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.guide.dao.AbstractDaoImpl;

import java.util.List;

/**
 * Created by Max on 09.08.2016.
 */
@Service
@Transactional(readOnly = false)
public class BasicService<T> {

    @Autowired
    private AbstractDaoImpl dao;

    private Class<T> type;

    public BasicService(Class<T> type) {
        this.type = type;
    }

    public T getById(Integer id) {
        return (T) dao.getById(id, type);
    }

    public List<T> getAll() {
        return dao.findAll(type);
    }

    public T create(T entity) {
        return (T) dao.create(entity);
    }

    public T update(T entity) {
        return (T) dao.update(entity);
    }

    public void delete(T entity) {
        dao.delete(entity);
    }

    public void deleteById(Integer id) {
        dao.delete(dao.getById(id, type));
    }

}
