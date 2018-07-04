package ph.inv.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import ph.inv.entity.Employee;
import ph.inv.service.EmployeeService;

@Component("employeeEditor")
public class EmployeeEditor extends PropertyEditorSupport {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if(StringUtils.hasText(text)) {
			
			Employee employee = employeeService.find(Long.parseLong(text));
			employee.setId(Long.parseLong(text));
			setValue(employee);
		} else {
			setValue(null);
		}
	}

	@Override
	public String getAsText() {
		Employee employee = (Employee) getValue();
		if(employee != null) {
			return employee.getId()+"";
		} else {
			return "";
		}
	}
}
