package ph.inv.controller;

import java.lang.reflect.ParameterizedType;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ph.inv.entity.BaseEntity;
import ph.inv.entity.Employee;
import ph.inv.service.AbstractService;
import ph.inv.util.StringUtil;

public abstract class AbstractController<T extends BaseEntity> {

	@Autowired
	private BeanFactory beanFactory;

	private Class<T> clazz;

	protected String entityName;
	
	protected AbstractService<T, Long> service;
	
	protected Validator validator;
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		
		final String serviceName = this.clazz.getSimpleName().toLowerCase() + "Service";
		
		if(this.service == null && beanFactory.containsBean(serviceName)) {
			this.service = (AbstractService<T, Long>) beanFactory.getBean(serviceName);
		}

		final String validatorName = this.clazz.getSimpleName().toLowerCase() + "Validator";
		
		if (this.validator == null && beanFactory.containsBean(validatorName)) {
			this.validator = (Validator) beanFactory.getBean(validatorName);
		}
		
		this.entityName = StringUtil.parseClassname(this.getClass()); 
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		if(validator != null)
			binder.setValidator(validator);
	}
	
	@RequestMapping("/")
	protected String index(Model model) {
		model.addAttribute(entityName, BeanUtils.instantiateClass(clazz));
		return entityName;
	}
	
	@ModelAttribute("menu")
	protected String getMenu() {
		return entityName;
	}
	
	@GetMapping("/edit/{id}")
	protected String edit(Model model, @PathVariable long id) {
		model.addAttribute(entityName,service.find(id));
		return entityName;
	}
	
	@PostMapping("/save")
	protected String save(@Valid @ModelAttribute T entity, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) 
			return entityName;
		
		if(entity.getId() != 0)
			service.update(entity);
		else
			service.save(entity);
		
		return "redirect:/" + entityName + "/";
	}
}
