package ph.inv.dao.impl;

import org.springframework.stereotype.Repository;

import ph.inv.dao.CustomerDao;
import ph.inv.entity.Customer;

@Repository(value="customerDao")
public class CustomerDaoImpl extends AbstractDaoImpl<Customer, Long> implements CustomerDao{

	public CustomerDaoImpl() {
		super(Customer.class);
	}

}
