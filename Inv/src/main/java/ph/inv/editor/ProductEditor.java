package ph.inv.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import ph.inv.entity.Employee;
import ph.inv.entity.Product;
import ph.inv.service.ProductService;

@Component("productEditor")
public class ProductEditor extends PropertyEditorSupport{

	@Autowired
	private ProductService productService;
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if(StringUtils.hasText(text)) {
			
			Product product = productService.find(Long.parseLong(text));
			product.setId(Long.parseLong(text));
			setValue(product);
		} else {
			setValue(null);
		}
	}

	@Override
	public String getAsText() {
		Product product = (Product) getValue();
		if(product != null) {
			return product.getId()+"";
		} else {
			return "";
		}
	}
}
