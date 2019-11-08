package pe.edu.upc.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

	@Secured("ROLE_USER")
	@GetMapping("/new")
	public String newSupervisor(Model model) {
		model.addAttribute("supervisor", new Supervisor());
		return "supervisor/supervisor";
	}

	
	@PostMapping("/save")
	public String saveSupervisor(@Valid Supervisor Supervisor, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "supervisor/supervisor";
		} else {
			int rpta = sUService.insert(Supervisor);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "/supervisor/supervisor";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
			model.addAttribute("listSupervisors", sUService.list());
			return "/supervisor/listSupervisors";
		}
	}

	@Secured("ROLE_USER")
	@RequestMapping("/delete")
	public String deleteSupervisor(Map<String, Object> model, @RequestParam(value = "id") Long id) {
		try {
			if (id != null && id > 0) {
				sUService.delete(id);
				model.put("mensaje", "Se eliminó correctamente");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar un supervisor");
		}
		model.put("listSupervisors", sUService.list());
		return "/supervisor/listSupervisors";
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
	public String findBySupervisor(Map<String, Object> model, @ModelAttribute Supervisor supervisor)
			throws ParseException {
		List<Supervisor> listSupervisors;
		supervisor.setName(supervisor.getName());
		listSupervisors = sUService.findByName(supervisor.getName());

		if (listSupervisors.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		model.put("listSupervisors", listSupervisors);
		return "supervisor/find";
	}


	@GetMapping("/detail1/{id}")
	public String detailsSupervisor(@PathVariable(value = "id") Long id, Model model) {
		try {
			Optional<Supervisor> supervisor = sUService.findById(id);

			if (!supervisor.isPresent()) {
				model.addAttribute("info", "El supervisor no existe");
				return "redirect:/supervisor/list";
			} else {
				model.addAttribute("supervisor", supervisor.get());
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}

		return "/supervisor/detail";
	}

}
