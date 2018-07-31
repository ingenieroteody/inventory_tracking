package ph.inv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ph.inv.dao.CustomerDao;
import ph.inv.entity.Customer;
import ph.inv.service.CustomerService;

@Service(value="customerService")
public class CustomerServiceImpl extends AbstractServiceImpl<Customer, Long> implements CustomerService{

	@Autowired
	private CustomerDao customerDao;
}
