package ph.inv.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ph.inv.entity.Color;
import ph.inv.entity.Employee;
import ph.inv.service.EmployeeService;


@Component(value="employeeValidator")
public class EmployeeValidator implements Validator{

	@Autowired
	private EmployeeService employeeService;
	
	public boolean supports(Class<?> clazz) {
		return Employee.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "field.required");
		
	}

}
