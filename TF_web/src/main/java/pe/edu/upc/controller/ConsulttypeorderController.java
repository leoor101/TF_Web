package pe.edu.upc.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import pe.edu.upc.entity.Consulttypeorder;
import pe.edu.upc.entity.Request;
import pe.edu.upc.service.IAccounting_OfficerService;
import pe.edu.upc.service.IConsulttypeService;
import pe.edu.upc.service.ITypeorderService;

@Controller
@SessionAttributes("consult")
@RequestMapping("/consults")
public class ConsulttypeorderController {

	@Autowired
	private IConsulttypeService consultServ;
	@Autowired
	private ITypeorderService typeServ;
	@Autowired
	private IAccounting_OfficerService accServ;



	@GetMapping("/listFindConsult")
	public String listRequest(Model model) {
		try {
			model.addAttribute("consulttypeorder", new Consulttypeorder());
			model.addAttribute("listconsulttypeorder", consultServ.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/consuttypeorder/findconsult";
	}

	@RequestMapping("/findconsult")
	public String findConsult(Map<String, Object> model, @ModelAttribute Consulttypeorder consulttypeorder)
			throws ParseException {

		List<Request> listrequest;
		consulttypeorder.setConsultName(consulttypeorder.getConsultName());
		listrequest = consultServ.findName(consulttypeorder.getConsultName());

		if (listrequest.isEmpty()) {
			model.put("mensaje", "No se encontró tipo de orden frecuente");
		}
		model.put("listconsulttypeorder", listrequest);
		return "consuttypeorder/findconsult";

	}

}
