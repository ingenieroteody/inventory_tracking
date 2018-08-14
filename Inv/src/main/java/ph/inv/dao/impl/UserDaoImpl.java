package ph.inv.dao.impl;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import ph.inv.dao.UserDao;
import ph.inv.entity.User;

@Repository(value="userDao")
public class UserDaoImpl extends AbstractDaoImpl<User, Long> implements UserDao{

	public UserDaoImpl() {
		super(User.class);
	}

	public UserDetails loadUserByUsername(String username) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(getPersistentClass());
		Root<User> user = criteriaQuery.from(User.class);
		criteriaQuery.where(criteriaBuilder.equal(user.get("username"), username));
		
		Query query = entityManager.createQuery(criteriaQuery);
		if(query.getResultList().size() != 0) {
			User u = (User) query.getSingleResult();
			return u;
		} else {
			throw new UsernameNotFoundException("");
		}
		
	}

}
