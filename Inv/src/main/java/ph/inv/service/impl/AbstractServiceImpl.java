package ph.inv.service.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.envers.RevisionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ph.inv.dao.AbstractDao;
import ph.inv.service.AbstractService;

public class AbstractServiceImpl<T, ID extends Serializable> implements AbstractService<T, ID>{

	@Autowired
	protected AbstractDao<T, ID> abstractDao;
	
	public void save(T t) {
		abstractDao.save(t);
	}
	
	public List<T> findAll() {
		return abstractDao.findAll();
	}
	
	public T find(long id) {
		return abstractDao.find(id);
	}

	public void remove(T t) {
		abstractDao.remove(t);
		
	}

	public List<T> findBySearch(String searchPhrase, String sortBy) {
		if(searchPhrase.isEmpty()) 
			return abstractDao.findAll();
			
		return abstractDao.findBySearch(searchPhrase, sortBy);
	}

	public void update(T t) {
		abstractDao.update(t);
	}

}
