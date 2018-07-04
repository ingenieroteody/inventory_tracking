package ph.inv.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Entity;

import org.hibernate.envers.RevisionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ph.inv.bean.AuditSearch;
import ph.inv.bean.Auditable;
import ph.inv.entity.BaseEntity;
import ph.inv.entity.Color;
import ph.inv.service.AbstractService;
import ph.inv.service.AuditService;
import ph.inv.service.ColorService;

@Controller
@RequestMapping("/audit")
public class AuditController {

	@Autowired
	private AuditService auditService;
	
	@GetMapping("/")
	public String index() {
		return "audit";
	}
	
	@GetMapping("/search")
	public String search(Model model, @RequestParam MultiValueMap<String, String> params) {
		AuditSearch search = new AuditSearch();
		search.setEntity(params.get("entity").get(0));
		search.setRevisionType(RevisionType.valueOf(params.get("type").get(0)));
		
		System.out.println("Size: " + auditService.audits(search.getEntity(),search.getRevisionType()).size());
		for(Auditable c : auditService.audits(search.getEntity(),search.getRevisionType())) {
			System.out.println(c.toString());
		}
		
		return "audit";
	}
	
	
	@ModelAttribute("entities")
	public List<String> getEntities() {
		List<String> entities = new ArrayList<String>();
		ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(true);
		scanner.addIncludeFilter(new AnnotationTypeFilter(Entity.class));
		
		for(BeanDefinition bd : scanner.findCandidateComponents("ph.inv.entity")) {
			entities.add(bd.getBeanClassName());
		}		
		return entities;
	}
}
