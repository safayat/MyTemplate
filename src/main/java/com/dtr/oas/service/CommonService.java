package com.dtr.oas.service;

import com.dtr.oas.dao.CommonDAO;
import com.dtr.oas.util.PaginationParam;
import com.dtr.oas.util.Strings;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class CommonService {

	@Autowired
	private CommonDAO dao;

	@Transactional
	public <E,I extends Serializable> E get(Class<E> entityClass, I id){
		return dao.get(entityClass, id);
	}

	@Transactional
	public <E> void saveOrUpdate(E model) throws Exception{
		dao.saveOrUpdate(model);
	}

	@Transactional
	public <E> List<E> getAll(Class<E> entityClass, int limit, int offset) throws Exception{
		return dao.getAll(entityClass, limit, offset);
	}

	@Transactional
	public <E> List<E> getAll(Class<E> entityClass) throws Exception{
		return dao.getAll(entityClass);
	}


	@Transactional
	public <E> List<E> search(Class<E> entityClass,Iterator<Map.Entry> itr, PaginationParam param) throws Exception{
		StringBuilder sb = new StringBuilder("from " + entityClass.getSimpleName() + " where 1=1");
		buildHql(itr,sb);
		sb.append(param.toQuery());
		return dao.getQuery(sb.toString()).list();
	}

	@Transactional
	public <E> E uniqueSearch(Class<E> entityClass, String exp, Object value) throws Exception{
		Query q = dao.getQuery("from " + entityClass.getSimpleName() + " where " + exp + ":param");
		q.setParameter("param",value);
		return (E)q.uniqueResult();
	}

	public void buildHql(Iterator<Map.Entry> itr,StringBuilder hql){
		while (itr.hasNext()){
			Map.Entry entry = itr.next();
			if(Strings.isNotEmpty(entry.getValue())){
				hql.append(" and ").append(entry.getKey()).append(entry.getValue());
			}
		}
	}

/*
	public List<Long> getPrimaryKeyList(List<BaseEntity> rows){
		Iterator<BaseEntity> itr = rows.iterator();
		List<Long> keyList = new ArrayList<>();
		while (itr.hasNext()){
			BaseEntity baseEntity = itr.next();
			keyList.add(baseEntity.getId());
		}
		return keyList;
	}

*/


}
