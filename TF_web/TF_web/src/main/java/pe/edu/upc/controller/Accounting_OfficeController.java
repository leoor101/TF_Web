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

import pe.edu.upc.entity.Accounting;
import pe.edu.upc.service.IAccounting_OfficerService;

@Controller
@RequestMapping("/accountings")
public class Accounting_OfficeController 
{
	@Autowired
	private IAccounting_OfficerService aService;
	
	@RequestMapping("/index")
	public String goWelcome() {
		return "main";
	}
	@Secured("ROLE_USER")
	@GetMapping("/new")
	public String newAccounting(Model model) {
		model.addAttribute("accounting", new Accounting());
		return "accounting/accounting";
		
	}
	@Secured("ROLE_USER")
	@PostMapping("/save")
	public String saveAccounting(@Valid Accounting accounting, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "accounting/accounting";
		} else {
			int rpta = aService.insert(accounting);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "/accounting/accounting";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				model.addAttribute("listAccounting", aService.list());
				status.setComplete();
			}
		}
		return "/accounting/listAccounting";
	}
	@Secured("ROLE_USER")
	@GetMapping("/list")
	public String listAccountings(Model model) {
		try {
			model.addAttribute("accounting", new Accounting());
			model.addAttribute("listAccounting", aService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/accounting/listAccounting";
	}
	@Secured("ROLE_USER")
	@RequestMapping("/delete")
	public String delete(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				aService.delete(id);
				model.put("mensaje", "Se eliminó correctamente");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se pudo eliminar");
		}
		model.put("listAccounting", aService.list());

		return "/accounting/listAccounting";

	}
	@Secured("ROLE_USER")
	@GetMapping("/detail/{id}")
	public String detailsAccounting(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Accounting> accounting = aService.listarAccountingOfficerId(id);
			if (!accounting.isPresent()) {
				model.addAttribute("info", "El encargado no existe");
				return "redirect:/accountings/list";
			} else {
				model.addAttribute("accounting", accounting.get());
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/accounting/update";
	}

	@Secured("ROLE_USER")
	@GetMapping("/listFind")
	public String listAccountingsFind(Model model) {
		try {
			model.addAttribute("accounting", new Accounting());
			model.addAttribute("listAccounting", aService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/accounting/find";
	}
	@Secured("ROLE_USER")
	@RequestMapping("/find")
	public String findByAccounting(Map<String, Object> model, @ModelAttribute Accounting accounting) throws ParseException {

		List<Accounting> listAccounting;
		accounting.setDNI(accounting.getDNI());
		listAccounting = aService.findByDNI(accounting.getDNI());

		if (listAccounting.isEmpty()) {
			model.put("mensaje", "No se pudo encontrar");
		}
		model.put("listAccounting", listAccounting);
		return "accounting/find";

	}
	@Secured("ROLE_USER")
	@PostMapping("/savemodify")
	public String saveAccounting2(@Valid Accounting accounting, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "accounting/accounting";
		} else {
			aService.insertmodified(accounting);

			model.addAttribute("mensaje", "Se modificó correctamente");
			model.addAttribute("listAccounting", aService.list());
			status.setComplete();
			return "/accounting/listAccounting";
		}
	}
}
