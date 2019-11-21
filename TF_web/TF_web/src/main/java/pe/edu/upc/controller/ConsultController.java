package pe.edu.upc.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import pe.edu.upc.entity.Consult;
import pe.edu.upc.service.IAccounting_OfficerService;
import pe.edu.upc.service.IConsultService;
import pe.edu.upc.service.IProduct_RequirementService;
import pe.edu.upc.service.ITypeorderService;
import pe.edu.upc.service.IUsersService;

@Controller
@SessionAttributes("consult")
@RequestMapping("/consults")
public class ConsultController {
	@Autowired
	private IConsultService consultServ;
	@Autowired
	private IUsersService userServ;
	@Autowired
	private IProduct_RequirementService proServ;
	@Autowired
	private ITypeorderService typeServ;
	@Autowired
	private IAccounting_OfficerService accServ;

	@GetMapping("/detail/{id}")
	public String detailConsult(@PathVariable(value = "id") Long id, Model model) {

		try {
			Optional<Consult> consult = consultServ.fetchByConsultIdWithUserWhithRequiDetailsWithProduct(id);

			if (!consult.isPresent()) {
				model.addAttribute("error", "La solicitud no existe");
				return "redirect:/supervisor/list";
			}

			model.addAttribute("consult", consult.get());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}

		return "consult/detail";
	}

	@GetMapping("/listFind")
	public String listConsult(Model model) {
		try {
			model.addAttribute("consult", new Consult());
			model.addAttribute("listconsult", consultServ.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/consult/find";
	}

	@GetMapping("/consultTypes")
	public String listAllConsultTypes(Map<String, Object> model) {
		model.put("consultTypes", consultServ.listConsultType());
		return "/consult/consultTypes";
	}

}
