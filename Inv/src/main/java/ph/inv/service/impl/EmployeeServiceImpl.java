package ph.inv.service.impl;

import org.springframework.stereotype.Service;

import ph.inv.entity.Employee;
import ph.inv.service.EmployeeService;

@Service(value="employeeService")
public class EmployeeServiceImpl extends AbstractServiceImpl<Employee, Long> implements EmployeeService {

}
