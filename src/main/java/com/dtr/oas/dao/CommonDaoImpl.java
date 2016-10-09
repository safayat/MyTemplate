package com.dtr.oas.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



@Component
public abstract class CommonDaoImpl{

	protected SessionFactory sessionFactory;

	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	public <E,I extends Serializable> E  get(Class<E> entityClass,I id) {
		return (E)getCurrentSession().get(entityClass,id);
	}

	public <E> void saveOrUpdate(E e) throws Exception{
		getCurrentSession().saveOrUpdate(e);
	}


	public<E> void delete(E e) throws Exception{
		getCurrentSession().delete(e);
	}


	public<E> List<E> findByCriteria(Class entityClass,Criterion criterion) {

		Criteria criteria = getCurrentSession().createCriteria(entityClass);
		criteria.add(criterion);

		return (List<E>)criteria.list();
	}

	public Criteria getCriteria(Class entityClass,Criterion criterion) {

		Criteria criteria = getCurrentSession().createCriteria(entityClass);
		criteria.add(criterion);
		return criteria;
	}
	public Criteria getCriteria(Class entityClass) {

		return getCurrentSession().createCriteria(entityClass);
	}
	public <E> List<E> getPaginatedData(Criteria criteria, int offset, int limit) {
		if(limit>100 || limit<=0){
			limit = 100;
		}
		if(offset <0){
			offset = 0;
		}
		return criteria.setFirstResult(offset).setMaxResults(limit).list();
	}

	public <E> List<E> getPaginatedData(Query query, int offset, int limit) {
		if(limit>100 || limit<=0){
			limit = 100;
		}
		if(offset <0){
			offset = 0;
		}
		return query.setFirstResult(offset).setMaxResults(limit).list();
	}

	public Criteria getCriteria(Class entityClass, List<Criterion> criterionList) {

		Criteria criteria = getCurrentSession().createCriteria(entityClass);
		for(Criterion criterion : criterionList){
			criteria.add(criterion);
		}
		return criteria;
	}
	public<E> List<E> findByCriteriaList(Class entityClass, List<Criterion> criterionList) {

		Criteria criteria = getCurrentSession().createCriteria(entityClass);
		for(Criterion criterion : criterionList){
			criteria.add(criterion);
		}
		return (List<E>)criteria.list();
	}


	public<E> List<E> getAll(Class entityClass) {
		return getCurrentSession().createQuery(" from " + entityClass.getSimpleName()).setMaxResults(100).list();
	}
	public<E> List<E> getAll(Class entityClass, int limit, int offset) {
		if(limit>100 || limit<=0){
			limit = 100;
		}
		if(offset <0){
			offset = 0;
		}

		return getCurrentSession().createQuery(" from " + entityClass.getSimpleName()).setMaxResults(limit).setFirstResult(offset).list();
	}

	public<E> List<E> getAllWithOpenSession(Class entityClass) {
		Session session = sessionFactory.openSession();
		try{
			return session.createQuery(" from " + entityClass.getSimpleName()).list();

		}catch (Exception e){
			e.printStackTrace();
		}
		finally {
			try{
				session.close();

			}catch (Exception e){
			}
		}
		return null;
	}

	@Transactional
	public <E,I extends Serializable> List<E> in(Class<E> entityClass,List<I> value) throws Exception{
		if(value.size() == 0){
			return new ArrayList<>();
		}
		return getCriteria(entityClass, Restrictions.in("id", value)).list();
	}


	public Query getQuery(String hql) {
		return getCurrentSession().createQuery(hql);
	}
	public Query getSqlQuery(String hql) {
		return getCurrentSession().createSQLQuery(hql);
	}
	public<E> List<E> getByHql(String hql) {
		return getCurrentSession().createQuery(hql).list();
	}

	public void updateByHql(String hql) throws Exception{
		Query query = getQuery(hql);
		query.executeUpdate();

	}


/*
    public DaoResult updateSingle(Class entityClass ,String filedName, Object value){
        Query query = getQuery(" update " + entityClass.getSimpleName() + " set " + filedName + "=:field");
        query.setParameter("field", value);
        query.executeUpdate();
        return new DaoResult();

    }
*/


	
	@SuppressWarnings("unchecked")
	public <T> T getById(T obj, int id) throws Exception {
		
		if (obj == null) {
			
			throw new Exception("Object is Null");
			
		}
		
		
		if (id <= 0) {
			
			throw new Exception("Invalid Id");
			
		}
		
		
		Session session = sessionFactory.openSession();
		T dto = obj;
		
		
		try {
			
			dto = (T) session.get(obj.getClass(), id);
			
		} catch (Exception e) {
			
			throw e;
			
		}finally{
			
			session.close();
			
		}
		
		
		return dto;
		
	}



}
