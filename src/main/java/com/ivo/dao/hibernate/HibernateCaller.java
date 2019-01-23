package com.ivo.dao.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.io.Serializable;
import java.util.List;

/**
 * @author wangjian
 * @date 2018/12/19
 */
public class HibernateCaller extends HibernateDaoSupport {

    protected Log logger = LogFactory.getLog(this.getClass());


    /**
     *
     * @param hql
     * @param values
     * @return
     */
    public Query createQuery(String hql, Object... values) {
        Query query = getSession().createQuery(hql);
        for (int i = 0; i < values.length; i++) {
            query.setParameter(i, values[i]);
        }
        return query;
    }


    /**
     * Create Criteria model
     * @param entityClass
     * @param criterions
     * @return
     */
    public Criteria createCriteria(Class entityClass, Criterion... criterions) {
        Criteria criteria = getSession().createCriteria(entityClass);
        for (Criterion c : criterions) {
            criteria.add(c);
        }
        return criteria;
    }

    /**
     *
     * @param entityClass
     * @param orderBy
     * @param isAsc
     * @param criterions
     * @return
     */
    public Criteria createCriteria(Class entityClass, String orderBy, boolean isAsc, Criterion... criterions) {
        Criteria criteria = createCriteria(entityClass, criterions);
        if (isAsc) {
            criteria.addOrder(Order.asc(orderBy));
        } else {
            criteria.addOrder(Order.desc(orderBy));
        }
        return criteria;
    }

    /**
     *
     * @param hql
     * @return
     */
    public List find(String hql) {
        return getHibernateTemplate().find(hql);
    }

    /**
     *
     * @param hql
     * @param values
     * @return
     */
    public List find(String hql, Object... values) {
        return getHibernateTemplate().find(hql, values);
    }

    /**
     * search by hql Top result
     * @param hql
     * @param maxResult
     * @param values
     * @return
     */
    public List find(String hql, int maxResult, Object... values) {
        Query query = this.createQuery(hql, values);
        query.setFirstResult(0);
        query.setMaxResults(maxResult);
        List list = query.list();
        return list;
    }

    /**
     * Get object by id
     * @param clazz
     * @param id
     * @param <T>
     * @return
     */
    public <T>T getObject(Class<T> clazz, Serializable id) {
        T o = getHibernateTemplate().get(clazz, id);
        return o;
    }

    /**
     *
     * @param clazz
     * @param columnName
     * @param columnValue
     * @param <T>
     * @return
     */
    public <T>T getObject(Class<T> clazz, String columnName, String columnValue) {
        T o = (T)getSession().createCriteria(clazz)
                .add(Restrictions.like(columnName, columnValue))
                .uniqueResult();
        if (o == null) {
            throw new ObjectRetrievalFailureException(clazz, columnName);
        }
        return o;
    }

    /**
     *
     * @param clazz
     * @return
     */
    public List loadAll(Class clazz) {
        return getHibernateTemplate().loadAll(clazz);
    }

    /**
     *
     * @param o
     */
    public void saveObject(Object o) {
        getHibernateTemplate().saveOrUpdate(o);
    }

    /**
     *
     * @param o
     */
    public void saveOrUpdateObject(Object o) {
        getHibernateTemplate().saveOrUpdate(o);
    }

    /**
     *
     * @param o
     */
    public void updateObject(Object o) {
        getHibernateTemplate().update(o);
    }

    /**
     *
     * @param hql
     * @param values
     */
    public void update(String hql, Object... values) {
        Query query = getSession().createQuery(hql);
        for (int i = 0; i < values.length; i++) {
            query.setParameter(i, values[i]);
        }
        query.executeUpdate();
    }

    /**
     *
     * @param clazz
     * @param id
     */
    public void removeObject(Class clazz, Serializable id) {
        getHibernateTemplate().delete(getObject(clazz, id));
    }

    /**
     *
     * @param obj
     */
    public void removeObject(Object obj) {
        getHibernateTemplate().delete(obj);
    }

    /**
     *
     * @param sql
     * @param values
     * @return
     */
    public List sqlQuery(String sql, String... values) {
        Query query = getSession().createSQLQuery(sql);
        for (int i = 0; i < values.length; i++) {
            query.setParameter(i, values[i]);
        }
        List list = query.list();
        return list;
    }

    /**
     *
     * @param sql
     * @return
     */
    public List sqlQuery(String sql) {
        Query query = getSession().createSQLQuery(sql);
        List list = query.list();
        return list;
    }

    public <T> List<T> sqlEntityQuery(String sql, Class<T> clazz) {
        Session session = getSession();
        Query query = session.createSQLQuery(sql).addEntity(clazz);
        List<T> list = query.list();
        return list;
    }


    /**
     * 按Criteria查询对象列表.
     * @param entityClass
     * @param criterions
     * @return
     */
    public List find(final Class entityClass, final Criterion... criterions) {
        return createCriteria(entityClass, criterions).list();
    }


    public Session getSession() {
        return getSessionFactory().getCurrentSession();
    }
}
