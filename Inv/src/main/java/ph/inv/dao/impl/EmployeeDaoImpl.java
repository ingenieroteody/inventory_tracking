package ph.inv.dao.impl;

import org.springframework.stereotype.Repository;

import ph.inv.dao.EmployeeDao;
import ph.inv.entity.Employee;

@Repository(value="employeeDao")
public class EmployeeDaoImpl extends AbstractDaoImpl<Employee, Long> implements EmployeeDao {

	public EmployeeDaoImpl() {
		super(Employee.class);
	}

}
