package ph.inv.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ph.inv.bean.CurrentInventory;
import ph.inv.bean.DataTable;
import ph.inv.bean.Pagination;
import ph.inv.entity.Color;
import ph.inv.entity.Employee;
import ph.inv.entity.Inventory;
import ph.inv.entity.Product;
import ph.inv.service.ColorService;
import ph.inv.service.EmployeeService;
import ph.inv.service.InventoryService;
import ph.inv.service.ProductService;
import ph.inv.util.StringUtil;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ColorService colorService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private InventoryService inventoryService;
	
	@RequestMapping(path="/getallproducts", method=RequestMethod.GET)
	public DataTable<String> getAllProducts(@RequestParam MultiValueMap<String, String> params) {
		DataTable<String> dataTable = new DataTable<String>();
		List<List<String>> jsonProducts = new ArrayList<List<String>>();
		List<Product> products = productService.findAll();
		
		for(Product p : products) {
			List<String> jsonData = new LinkedList<String>();
			jsonData.add(p.getId()+"");
			jsonData.add(p.getCode());
			jsonData.add(p.getName());
			jsonProducts.add(jsonData);
		}
		dataTable.setData(jsonProducts);

		return dataTable;
	}
	
	@RequestMapping(path="/getallcolors", method=RequestMethod.GET) 
	public DataTable<String> getAllColors(@RequestParam MultiValueMap<String, String> params) { 
		DataTable<String> dataTable = new DataTable<String>();
		List<List<String>> jsonColors = new ArrayList<List<String>>();
		List<Color> colors = colorService.findAll();
		
		for(Color p : colors) {
			List<String> jsonData = new LinkedList<String>();
			jsonData.add(p.getId()+"");
			jsonData.add(p.getCode());
			jsonData.add(p.getName());
			jsonColors.add(jsonData);
		}
		dataTable.setData(jsonColors);

		return dataTable;
	}	
	
	@RequestMapping(path="/getallemployees", method=RequestMethod.GET) 
	public DataTable<String> getAllEmployees(@RequestParam MultiValueMap<String, String> params) { 
		DataTable<String> dataTable = new DataTable<String>();
		List<List<String>> jsonEmployees = new ArrayList<List<String>>();
		List<Employee> employees = employeeService.findAll();
		
		for(Employee e : employees) {
			List<String> jsonData = new LinkedList<String>();
			jsonData.add(e.getId()+"");
			jsonData.add(e.getFirstname());
			jsonData.add(e.getLastname());
			jsonData.add(e.getPosition().getValue());
			jsonEmployees.add(jsonData);
		}
		dataTable.setData(jsonEmployees);

		return dataTable;
	}	
	
	@RequestMapping(path="/getallinventory", method=RequestMethod.GET, produces="application/json") 
	public DataTable<String> getAllInventory(@RequestParam MultiValueMap<String, String> params) { 
		DataTable<String> dataTable = new DataTable<String>();
		List<List<String>> jsonInventories = new ArrayList<List<String>>();
		List<Inventory> inventories = inventoryService.findAll();
		for(Inventory i : inventories) {
			List<String> jsonData = new LinkedList<String>();
			jsonData.add(i.getId()+"");
			jsonData.add(i.getDate());
			jsonData.add(i.getNumberCode());
			final String productName = "[" + i.getProduct().getCode() + "] " + i.getProduct().getName(); 
			jsonData.add(productName);
			final String colorName = "[" + i.getColor().getCode() + "] " + i.getColor().getName();
			jsonData.add(colorName);
			jsonData.add(i.getSize().getValue());
			jsonData.add(i.getStatus().getValue());
			final String employeeName = i.getEmployee().getLastname() + ", " + i.getEmployee().getFirstname();
			jsonData.add(employeeName);
			jsonInventories.add(jsonData);
		}
		
		dataTable.setData(jsonInventories);
		return dataTable;
	}	
	
	@RequestMapping(path="/getcurrentinventory", method=RequestMethod.GET, produces="application/json") 
	public DataTable<String> getCurrentInventory(@RequestParam MultiValueMap<String, String> params) { 
		DataTable<String> dataTable = new DataTable<String>();
		List<List<String>> jsonInventories = new ArrayList<List<String>>();
		List<CurrentInventory> currentInventory = inventoryService.getCurrentStock();

		for(CurrentInventory ci : currentInventory) {
			List<String> jsonData = new LinkedList<String>();
			jsonData.add(ci.getQuantity()+"");
			jsonData.add(ci.getItemCode());
			jsonData.add(ci.getItemName());
			jsonData.add(ci.getItemSize().getValue());
			jsonData.add(ci.getItemStatus().getValue());
			jsonData.add(ci.getColorName());
			jsonInventories.add(jsonData);
		}
		
		dataTable.setData(jsonInventories);
		return dataTable;
	}	
	
	
	@RequestMapping(path="/findproduct", method=RequestMethod.GET, produces="application/json")
	public List<Product> findProduct(@RequestParam MultiValueMap<String, String> params) {
		List<Product> products = productService.findBySearch((String) params.getFirst("keyword"), "id");
		return products;
	}
	
	public static void main(String [] args) {
	}
}
