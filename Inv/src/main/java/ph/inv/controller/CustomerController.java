package ph.inv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ph.inv.entity.Customer;

@Controller
@RequestMapping("/customer")
public class CustomerController extends AbstractController<Customer>{

}
