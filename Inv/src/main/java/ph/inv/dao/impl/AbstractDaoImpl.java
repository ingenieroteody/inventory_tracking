package ph.inv.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import ph.inv.annotation.Searchable;
import ph.inv.dao.AbstractDao;
import ph.inv.entity.BaseEntity;

@Transactional
public abstract class AbstractDaoImpl<T, ID extends Serializable> implements AbstractDao<T, ID>{

	@PersistenceContext
	protected EntityManager entityManager;
	
	private Class<T> persistentClass;
	
	public AbstractDaoImpl(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}
	
	public Class<T> getPersistentClass() {
		return persistentClass;
	}
	
	public void save(T t) {
		entityManager.persist(t);
	}
	
	public void update(T t) {
		entityManager.merge(t);
	}
	
	public List<T> findAll() {
		return entityManager.createQuery("FROM " + persistentClass.getName()).getResultList();
	}
	
	public T find(long id) {
		return entityManager.find(getPersistentClass(), id);
		
	}
	
	public void remove(T t) {
		entityManager.remove(t);
	}
	
	public List<T> findBySearch(String searchPhrase, String sortBy) {
		Map<String,List<String>> searchableFields = new LinkedHashMap<String,List<String>>();
		traverseSearchableFields(persistentClass, Searchable.class, searchableFields);
		final String className = persistentClass.getSimpleName();
		final String classNameInitial = className.substring(0,1).toLowerCase();
		final String selectQuery = "SELECT " + classNameInitial + " FROM " + className + " " + classNameInitial;
		
		String innerJoin = "";
		String where = " WHERE ";
		
		int mapSize = searchableFields.size();
		System.out.println("MapSize : " + mapSize);
		int tableCtr = 0;
		for(Map.Entry<String, List<String>> table : searchableFields.entrySet()) {
			if(table.getKey().equals(persistentClass.getSimpleName())==false) {
				innerJoin = innerJoin + " INNER JOIN " + table.getKey();
			}
			
			tableCtr++;
			 
			int columnCtr = 0;
			for(String column : table.getValue()) {
				
				if(table.getKey().equals(persistentClass.getSimpleName())) {
					where = where + column + " LIKE :searchPhrase ";
				} else {
					where = where + table.getKey() + "." + column + " LIKE :searchPhrase ";
				}
				
				columnCtr++;
				
				if(tableCtr==mapSize && columnCtr==table.getValue().size()) {
					System.out.println("TableSize: " + tableCtr + "=" + mapSize + " Column Size: " + table.getValue().size() + "=" + columnCtr + " " + (tableCtr==mapSize) + " : " + (columnCtr < table.getValue().size()));
				} else {
					System.out.println("TableSize: " + tableCtr + "=" + mapSize + " Column Size: " + table.getValue().size() + "=" + columnCtr + " " + (tableCtr==mapSize) + " : " + (columnCtr < table.getValue().size()));
					where = where + " OR ";
				}
				
			}
		}
		
		final String searchCriteria = selectQuery + " " + innerJoin + " " + where;
		System.out.println("SearchPhrase: " + searchCriteria);
		TypedQuery<T> query = entityManager.createQuery(searchCriteria, persistentClass);
		query.setParameter("searchPhrase", "%"+searchPhrase+"%");
		return query.getResultList();
	}
	
	protected Map<String,List<String>> traverseSearchableFields(Class clazz, Class annotation, Map<String,List<String>> searchableFields) {
		//System.out.println("Clazz: " + clazz.getName() + " : " + clazz.getSimpleName());
		List<String> fields = new ArrayList<String>();
		for(Field field : clazz.getDeclaredFields()) {
			if(field.isAnnotationPresent(annotation)) {
				if(BaseEntity.class.isAssignableFrom(field.getType())) {
					//System.out.println("	Field: " + field.getName() + " : " + field.getType().getCanonicalName());
					try {
						Class<?> clasz = Class.forName(field.getType().getCanonicalName());
						traverseSearchableFields(clasz, annotation, searchableFields);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					fields.add(field.getName());
					//System.out.println("    " + clazz.getSimpleName() + "." + field.getName() + " :: " + (persistentClass.equals(clazz)));
				}
			}
			searchableFields.put(clazz.getSimpleName(),fields);
		}
		return searchableFields;
	}
}
