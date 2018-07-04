package ph.inv.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ph.inv.entity.Color;
import ph.inv.service.ColorService;


@Component(value="colorValidator")
public class ColorValidator implements Validator{

	@Autowired
	private ColorService colorService;
	
	public boolean supports(Class<?> clazz) {
		return Color.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "field.required");
		
		Color color = (Color) target;
		
		if(!color.getCode().isEmpty()) {
			if(color.getId() == 0) {
				if(colorService.findByCode(color.getCode()) != null) {
					errors.rejectValue("code", "field.already.exists",new Object [] {color.getCode()},"[{0}] code already exists");
				}
			}
		}
	}

}
