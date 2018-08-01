package ph.inv.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import ph.inv.entity.Customer;
import ph.inv.service.CustomerService;

@Component("customerEditor")
public class CustomerEditor extends PropertyEditorSupport {

	@Autowired
	private CustomerService customerService;
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if(StringUtils.hasText(text)) {
			
			Customer customer = customerService.find(Long.parseLong(text));
			customer.setId(Long.parseLong(text));
			setValue(customer);
		} else {
			setValue(null);
		}
	}

	@Override
	public String getAsText() {
		Customer customer = (Customer) getValue();
		if(customer != null) {
			return customer.getId()+"";
		} else {
			return "";
		}
	}
}
