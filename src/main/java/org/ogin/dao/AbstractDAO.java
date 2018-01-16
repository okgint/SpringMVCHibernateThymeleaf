package org.ogin.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * @author ogin
 */
public abstract class AbstractDAO<PK extends Serializable, T> {
    private final Class<T> persistentClass;
    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

    protected T getByKey(PK key) {
        return (T) entityManager.find(persistentClass, key);
    }

    protected void persist(T entity) {
        entityManager.persist(entity);
    }

    protected void update(T entity) {
        entityManager.merge(entity);
    }

    protected void delete(T entity) {
        entityManager.remove(entity);
    }

    protected Criteria createEntityCriteria(){
        Session session = getEntityManager().unwrap(Session.class);

        return session.createCriteria(persistentClass);
    }
}
