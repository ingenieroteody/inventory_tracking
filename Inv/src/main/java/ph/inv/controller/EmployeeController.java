package ph.inv.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ph.inv.entity.Employee;
import ph.inv.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController extends AbstractController<Employee> {

	@Autowired
	private EmployeeService employeeService;
	
	public String delete() {
		return "employee";
	}
	
}
