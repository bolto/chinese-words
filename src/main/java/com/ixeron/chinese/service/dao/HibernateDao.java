package com.ixeron.chinese.service.dao;

import org.hibernate.NonUniqueObjectException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Basic DAO operations dependent with Hibernate's specific classes. @see
 * SessionFactory
 */

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class HibernateDao<E, K extends Serializable> implements
        GenericDao<E, K> {

    @Autowired
    private SessionFactory sessionFactory;
    protected Class<? extends E> daoType;

    @SuppressWarnings("unchecked")
    public HibernateDao() {
        daoType = (Class<E>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

//    @Autowired
//    public void setSessionFactory(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }

    protected Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void add(E entity) {
        currentSession().save(entity);
    }

    public void update(E entity) {
        try{
            currentSession().saveOrUpdate(entity);
        }catch(NonUniqueObjectException ex1){
            try{
                //System.err.format("saveOrUpdate failed with NonUniqueObjectException.  trying merge() now.\n");
                currentSession().merge(entity);
            }catch(IllegalStateException ex){
                //ex.printStackTrace();
            }
        }
    }

    public void remove(E entity) {
        currentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public E find(K key) {
        return (E) currentSession().get(daoType, key);
    }

    @SuppressWarnings("unchecked")
    public List<E> list() {
        return currentSession().createCriteria(daoType).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
    }

    @SuppressWarnings("unchecked")
    public List<E> list(Integer max) {
        return currentSession().createCriteria(daoType).setMaxResults(max).list();
    }
}