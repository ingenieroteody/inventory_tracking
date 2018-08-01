package ph.inv.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ph.inv.entity.Mto;
import ph.inv.service.MtoService;

@Component(value="mtoValidator")
public class MtoValidator implements Validator {

	@Autowired
	private MtoService mtoService;
	
	public boolean supports(Class<?> clazz) {
		return Mto.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		Mto mto = (Mto) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "orderDate", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pickupDate", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "color", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "size", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "employee", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "field.required");
		
		if(!mto.getPickupDate().isEmpty() && !mto.getOrderDate().isEmpty()) {
			try {
				Date pickup = new SimpleDateFormat("mm/dd/yyyy").parse(mto.getPickupDate());
				Date orderDate = new SimpleDateFormat("mm/dd/yyyy").parse(mto.getOrderDate());
				if(pickup.before(orderDate)) {
					errors.rejectValue("pickupDate", "", "Pick-up date should be greater than order date");
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		if(mto.getProduct() == null) {
			errors.rejectValue("product", "field.required");
		}	
	}

}
