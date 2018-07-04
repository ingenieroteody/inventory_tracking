package ph.inv.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.envers.RevisionType;

public interface AbstractDao<T, ID extends Serializable> {

	public void save(T t);
	
	public void update(T t);
	
	public List<T> findAll();
	
	public T find(long id);

	public void remove(T t);
	
	public List<T> findBySearch(String searchPhrase, String sortBy);
}
