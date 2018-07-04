package ph.inv.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ph.inv.service.InventoryService;

@Controller
public class DashboardController {
	
	@Autowired
	private InventoryService inventoryService;
	
	@RequestMapping("/")
	public String index(Map<String, Object> model) {

		model.put("message", "Hi dashboard!");
		return "dashboard";
	}
}
