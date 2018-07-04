package ph.inv.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ph.inv.entity.Inventory;
import ph.inv.service.InventoryService;

@Component(value="inventoryValidator")
public class InventoryValidator implements Validator{

	@Autowired
	private InventoryService inventoryService;
	
	public boolean supports(Class<?> clazz) {
		return Inventory.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		
		Inventory inventory = (Inventory) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "date", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numberCode", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "color", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "size", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "employee", "field.required");
		
		if(inventory.getProduct() == null) {
			errors.rejectValue("product", "field.required",new Object [] {inventory.getProduct()},"[{0}] does not exists");
		}
		
		
		if(inventoryService.findByNumberCode(inventory.getNumberCode()).size() > 0 && inventory.getId() == 0) {
			errors.rejectValue("numberCode", "field.duplicate",new Object [] {inventory.getNumberCode()},"[{0}] already exists");
		}
		
	}

}
