package ph.inv.dao.impl;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import ph.inv.dao.ColorDao;
import ph.inv.entity.Color;

@Repository(value="colorDao")
public class ColorDaoImpl extends AbstractDaoImpl<Color, Long> implements ColorDao{

	public ColorDaoImpl() {
		super(Color.class);
	}

	public Color findByCode(String code) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Color> criteriaQuery = criteriaBuilder.createQuery(getPersistentClass());
		Root<Color> color = criteriaQuery.from(Color.class);
		criteriaQuery.where(criteriaBuilder.like(color.get("code").as(String.class), "%"+code+"%"));
		
		Query query = entityManager.createQuery(criteriaQuery);
		
		if(query.getResultList().size() != 0)
			return (Color) query.getSingleResult();
		
		return null;
	}

}
