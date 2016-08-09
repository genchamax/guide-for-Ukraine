package ua.com.guide.service;

import org.springframework.beans.factory.annotation.Autowired;
import ua.com.guide.dao.AbstractDaoImpl;

import java.util.List;

/**
 * Created by Max on 09.08.2016.
 */
public class Service <T> {

    @Autowired
    private AbstractDaoImpl dao;

    private Class<T> type;

    public Service(Class<T> type) {
        this.type = type;
    }

    public T getById (Integer id) {
        return (T) dao.getById(id);
    }

    public List<T> getAll () {
        return dao.findAll();
    }

    public T create (T entity) {
        return (T) dao.create(entity);
    }

    public T update (T entity) {
        return (T) dao.update(entity);
    }

    
}
