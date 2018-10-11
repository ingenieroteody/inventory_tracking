package ph.inv.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ph.inv.editor.EmployeeEditor;
import ph.inv.editor.ProductEditor;
import ph.inv.editor.SystemCodesEditor;
import ph.inv.entity.Color;
import ph.inv.entity.Employee;
import ph.inv.entity.Inventory;
import ph.inv.entity.ItemMovement;
import ph.inv.entity.Product;
import ph.inv.entity.SystemCodes;
import ph.inv.service.ColorService;
import ph.inv.service.EmployeeService;
import ph.inv.service.SystemCodesService;

@Controller
@RequestMapping("/inventory")
public class InventoryController extends AbstractController<Inventory> {

	@Autowired
	public ColorService colorService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EmployeeEditor employeeEditor;
	
	@Autowired
	private ProductEditor productEditor;
	
	@Autowired
	private SystemCodesEditor systemCodesEditor;
	
	@Autowired
	private SystemCodesService systemCodesService;
	
	@Override
	protected String save(@Valid Inventory entity, BindingResult bindingResult) {
		SystemCodes currentStatus = entity.getStatus();
		
		if(currentStatus != null) {
			currentStatus = systemCodesService.find(entity.getStatus().getId());
			if (currentStatus.getKey().equals("DELIVERED") || currentStatus.getKey().equals("SOLD")) {
				ItemMovement itemMovement = new ItemMovement();
				itemMovement.setInventory(entity);
				itemMovement.setDate(new Date());
				itemMovement.setStatus(entity.getStatus());
				itemMovement.setStoreBranch(entity.getStoreBranch());
				entity.setItemMovements(new ArrayList<ItemMovement>(Arrays.asList(itemMovement)));
			} else if (currentStatus.getKey().equals("CHANGE_ITEM") ) {
				System.out.println("ItemValue: " + entity.getChangeItemValue());
				System.out.println("ItemName: " + entity.getChangeItemName());
				Inventory itemReplaced = service.find(entity.getChangeItemValue());
				ItemMovement itemMovement = new ItemMovement();
				itemMovement.setInventory(entity);
				itemMovement.setDate(new Date());
				itemMovement.setStatus(entity.getStatus());
				itemMovement.setStoreBranch(entity.getStoreBranch());
				itemMovement.setItemReplaced(itemReplaced);
				entity.setItemMovements(new ArrayList<ItemMovement>(Arrays.asList(itemMovement)));				
			}
		} else {
			SystemCodes inStock = systemCodesService.loadByCategoryAndKey("INVENTORY_STATUS", "IN_STOCK");
			ItemMovement itemMovement = new ItemMovement();
			itemMovement.setInventory(entity);
			itemMovement.setDate(entity.getCreateDate());
			itemMovement.setStatus(inStock);
			entity.setItemMovements(new ArrayList<ItemMovement>(Arrays.asList(itemMovement)));
			entity.setStatus(inStock);
		} 
		return super.save(entity, bindingResult);
	}
	
	@GetMapping("/edit/{id}")
	protected String edit(Model model, @PathVariable long id) {
		Inventory inventory = super.service.find(id);
		List<SystemCodes> statuses = systemCodesService.loadByCategory("INVENTORY_STATUS");
		
		List<ItemMovement> movements = inventory.getItemMovements();
		if(movements.size() > 0) {
			for(int x=movements.size()-1; x >= 0; x--) {
				ItemMovement m = movements.get(x);
				if(m.getStatus().getKey().equals("CHANGE_ITEM")) {
					inventory.setChangeItemName("["+m.getItemReplaced().getNumberCode() +"] "+ m.getItemReplaced().getProductName());
					inventory.setChangeItemValue(m.getItemReplaced().getId());
				}
			}
		}
		
		if(inventory.getStatus().getKey().equals("IN_STOCK")) {
			removeStatus(statuses, "CHANGE_ITEM","RETURN_ITEM","SOLD");
		} else if (inventory.getStatus().getKey().equals("DELIVERED")) {
			removeStatus(statuses, "IN_STOCK");
		}
		

		
		
		model.addAttribute("statuses", statuses);
		model.addAttribute(entityName,inventory);
		return entityName;
	}
	
	@ModelAttribute("employees")
	public List<Employee> getEmployees() {
		return employeeService.findAll();
	}
	
	@ModelAttribute("colors")
	public List<Color> getColors() {
		return colorService.findAll();
	}
	
	@ModelAttribute("sizes")
	public List<SystemCodes> getSizes() {
		return systemCodesService.loadByCategory("DRESS_SIZE");
	}
	
	@ModelAttribute("statuses")
	public List<SystemCodes> getStatuses() {
		return systemCodesService.loadByCategory("INVENTORY_STATUS");
	}

	@ModelAttribute("storeBranches")
	public List<SystemCodes> getStoreBranches() {
		return systemCodesService.loadByCategory("STORE_BRANCHES");
	}
	
	@Override
	protected void initBinder(WebDataBinder binder) {
		super.initBinder(binder);
		binder.registerCustomEditor(Employee.class, employeeEditor);
		binder.registerCustomEditor(Product.class, productEditor);
		binder.registerCustomEditor(SystemCodes.class, systemCodesEditor);
	}
	
	private List<SystemCodes> removeStatus(List<SystemCodes> statuses, String... removeStatuses ) {
		for(int x=0; x<removeStatuses.length; x++) {
			Iterator<SystemCodes> i = statuses.iterator();
			while(i.hasNext()) {
				final String KEY = i.next().getKey();
				if(KEY.compareTo(removeStatuses[x]) == 0) {
					i.remove();
				}
			}
		}
		
		return statuses;
	}
}
