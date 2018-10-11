package ph.inv.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hibernate.envers.DefaultRevisionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ph.inv.bean.AuditDisplay;
import ph.inv.bean.CurrentInventory;
import ph.inv.bean.DataTable;
import ph.inv.entity.Color;
import ph.inv.entity.Customer;
import ph.inv.entity.Employee;
import ph.inv.entity.Inventory;
import ph.inv.entity.Mto;
import ph.inv.entity.Product;
import ph.inv.service.ColorService;
import ph.inv.service.CustomerService;
import ph.inv.service.EmployeeService;
import ph.inv.service.InventoryService;
import ph.inv.service.MtoService;
import ph.inv.service.ProductService;

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
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private MtoService mtoService;
	
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
			jsonData.add(p.getPrice()+"");
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
			jsonData.add(i.getPrice()+"");
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
			jsonData.add(ci.getItemSize());
			jsonData.add(ci.getItemStatus());
			jsonData.add(ci.getColorName());
			jsonInventories.add(jsonData);
		}
		
		dataTable.setData(jsonInventories);
		return dataTable;
	}	
	
	
	@RequestMapping(path="/auditinventory", method=RequestMethod.GET, produces="application/json")
	public List<AuditDisplay> auditInventory(@RequestParam MultiValueMap<String, String> params) {
		List<Object []> inventory = inventoryService.getAuditTrail(Long.parseLong(params.getFirst("id")));
		
		List<AuditDisplay> auditDisplays = new ArrayList<AuditDisplay>();
		
		
		for(Object [] o : inventory) {
			List<String> jsonData = new LinkedList<String>();
			final Inventory i = (Inventory) o[0];
			final DefaultRevisionEntity revision = (DefaultRevisionEntity) o[1];
			System.out.println(i);
			System.out.println(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss.S").format(revision.getRevisionDate()) + " : " + revision.getTimestamp());
			System.out.println(o[2]+"");
			
			jsonData.add(i.getDate());
			jsonData.add(i.getNumberCode());
			jsonData.add(i.getProductName());
			jsonData.add(i.getColor().getName());
			jsonData.add(i.getSize().getValue());
			jsonData.add(i.getStatus().getValue());
			jsonData.add(i.getEmployee().getCompleteName());
			
			AuditDisplay auditDisplay = new AuditDisplay();
			auditDisplay.setRevisionDate(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss.S").format(revision.getRevisionDate()));
			auditDisplay.setEventType(o[2]+"");
			auditDisplay.setFields(jsonData);
			
			auditDisplays.add(auditDisplay);
		}
		
		return auditDisplays;
	}
	
	@RequestMapping(path="/getallmtos", method=RequestMethod.GET) 
	public DataTable<String> getAllMTOs(@RequestParam MultiValueMap<String, String> params) { 
		DataTable<String> dataTable = new DataTable<String>();
		List<List<String>> jsonMTOs = new ArrayList<List<String>>();
		List<Mto> mtos = mtoService.findAll();
		
		for(Mto e : mtos) {
			List<String> jsonData = new LinkedList<String>();
			jsonData.add(e.getId()+"");
			jsonData.add(e.getOrderDate());
			jsonData.add(e.getPickupDate());
			jsonData.add(e.getProduct().getCode() + " " + e.getProduct().getName());
			jsonData.add(e.getColor().getName());
			jsonData.add(e.getSize().getValue());
			jsonData.add(e.getCustomer().toString());
			jsonData.add(e.getEmployee().getCompleteName());
			jsonData.add(e.getStatus().getValue());
			jsonData.add(e.getPrice()+"");
			jsonMTOs.add(jsonData);
		}
		dataTable.setData(jsonMTOs);

		return dataTable;
	}	
	
	@RequestMapping(path="/getallcustomers", method=RequestMethod.GET) 
	public DataTable<String> getAllCustomers(@RequestParam MultiValueMap<String, String> params) { 
		DataTable<String> dataTable = new DataTable<String>();
		List<List<String>> jsonCustomers = new ArrayList<List<String>>();
		List<Customer> customers = customerService.findAll();
		
		for(Customer e : customers) {
			List<String> jsonData = new LinkedList<String>();
			jsonData.add(e.getId()+"");
			jsonData.add(e.getFirstname());
			jsonData.add(e.getLastname());
			jsonData.add(e.getMobileNumber());
			jsonData.add(e.getEmailAddress());
			jsonData.add(e.getAddress());
			jsonCustomers.add(jsonData);
		}
		dataTable.setData(jsonCustomers);

		return dataTable;
	}		
	
	@RequestMapping(path="/findproduct", method=RequestMethod.GET, produces="application/json")
	public List<Product> findProduct(@RequestParam MultiValueMap<String, String> params) {
		List<Product> products = productService.findBySearch((String) params.getFirst("keyword"), "id");
		return products;
	}
	
	@RequestMapping(path="/findinventoryitem", method=RequestMethod.GET, produces="application/json")
	public List<Inventory> findInventoryItem(@RequestParam Map<String, String> params) {
		System.out.println((String) params.get("keyword") +" : " + (String) params.get("id"));
		List<Inventory> inventories = inventoryService.findLikeNumberCode((String) params.get("keyword"), (String) params.get("id"));
		return inventories;
	}
	
	public static void main(String [] args) {
	}
}
