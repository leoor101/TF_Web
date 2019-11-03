package pe.edu.upc.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upc.entity.Supervisor;
import pe.edu.upc.service.ISupervisorService;


@Controller
@RequestMapping("/supervisor")
public class SupervisorController {
	@Autowired
	private ISupervisorService sUService;

	@GetMapping("/new")
	public String newSupervisor(Model model) {
		model.addAttribute("supervisor", new Supervisor());
		return "supervisor/supervisor";
	}

	@GetMapping("/save")
	public String saveSupervisor(@Valid Supervisor Supervisor, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "supervisor/supervisor";
		} else {
			int rpta = sUService.insert(Supervisor);
			if (rpta > 0) {
				model.addAttribute("message", "Ya existe");
				return "/supervisor/supervisor";
			} else {
				model.addAttribute("message", "Se guardó correctamente");
				status.setComplete();
			}
			model.addAttribute("listSupervisors", sUService.list());
			return "/supervisor/listSupervisors";
		}
	}

	public String deleteSupplier(Map<String, Object> model, @RequestParam(value = "SupervisorID") Long id) {
		try {
			if (id != null && id > 0) {
				sUService.delete(id);
				model.put("message", "Se eliminó correctamente");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("message", "No se puede eliminar un proveedor");
		}
		model.put("listSupervisors", sUService.list());
		return "";
	}

	@GetMapping("/list")
	public String listSupervisors(Model model) {
		try {
			model.addAttribute("supervisor", new Supervisor());
			model.addAttribute("listSupervisors", sUService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/supervisor/listSupervisors";
	}

	@GetMapping("/listFind")
	public String listSupervisorsFind(Model model) {
		try {
			model.addAttribute("supervisor", new Supervisor());
			model.addAttribute("listSupervisors", sUService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "supervisor/find";
	}

	@RequestMapping("/find")
	public String findBySupervisor(Map<String, Object> model, @ModelAttribute Supervisor supervisor) throws ParseException {
		List<Supervisor> listSupervisors;
		supervisor.setName(supervisor.getName());
		listSupervisors = sUService.findByName(supervisor.getName());
		
		if (listSupervisors.isEmpty()) {
			model.put("message", "No se encontró");
		}
		model.put("listCategories", listSupervisors);
		return "supervisor/find";
	}
}
