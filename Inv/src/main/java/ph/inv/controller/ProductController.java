package ph.inv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ph.inv.entity.Product;

@Controller
@RequestMapping("/product")
public class ProductController extends AbstractController<Product> {
	
}
