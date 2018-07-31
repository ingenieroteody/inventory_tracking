package ph.inv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import ph.inv.editor.EmployeeEditor;
import ph.inv.editor.ProductEditor;
import ph.inv.entity.Color;
import ph.inv.entity.Employee;
import ph.inv.entity.MTO;
import ph.inv.entity.Product;
import ph.inv.service.ColorService;
import ph.inv.service.EmployeeService;

@Controller
@RequestMapping("/mto")
public class MTOController extends AbstractController<MTO>{

	@Autowired
	private ColorService colorService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EmployeeEditor employeeEditor;
	
	@Override
	protected void initBinder(WebDataBinder binder) {
		super.initBinder(binder);
		binder.registerCustomEditor(Employee.class, employeeEditor);
	}
	
	@ModelAttribute("employees")
	public List<Employee> getEmployees() {
		return employeeService.findAll();
	}
	
	@ModelAttribute("colors")
	public List<Color> getColors() {
		return colorService.findAll();
	}
}
