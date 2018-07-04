package ph.inv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ph.inv.entity.Color;

@Controller
@RequestMapping("/color")
public class ColorController extends AbstractController<Color> {


	public String delete() {
		return "color";
	}
}
