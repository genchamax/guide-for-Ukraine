package ua.com.guide.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;

import java.util.List;

/**
 * Created by Max on 07.08.2016.
 */
public class AbstractDaoImpl<T> implements AbstractDao<T> {

    private Class<T> type;

    public AbstractDaoImpl(Class<T> type) {
        this.type = type;
    }

    @Autowired
    protected HibernateTemplate ht;

    public T getById(Integer id) {
        return ht.get(type, id);
    }

    public List<T> findAll() {
        return ht.loadAll(type);
    }

    public T create(T entity) {
        ht.save(entity);
        return entity;
    }

    public T update(T entity) {
        ht.getSessionFactory().getCurrentSession().clear(); // TODO: 08.08.2016 Why i am do this ?
        ht.saveOrUpdate(entity);
        return entity;
    }

    public void delete(T entity) {
        ht.delete(entity);
    }
}