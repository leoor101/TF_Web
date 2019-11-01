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

import pe.edu.upc.entity.Supplier;
import pe.edu.upc.service.ISupplierService;

@Controller
@RequestMapping("/")
public class SupplierController {

	@Autowired
	private ISupplierService suService;

	@RequestMapping("/")
	public String irWelcome() {
		return "welcome";
	}

	@GetMapping("/")
	public String newSupplier(Model model) {
		model.addAttribute("supplier", new Supplier());
		return "supplier/supplier";
	}

	public String saveSupplier(@Valid Supplier supplier, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "supplier/supplier";
		} else {
			int rpta = suService.insert(supplier);
			if (rpta > 0) {
				model.addAttribute("message", "Ya existe");
				return "/supplier/supplier";
			} else {
				model.addAttribute("message", "Se guardó correctamente");
				status.setComplete();
			}
			model.addAttribute("listSuppliers", suService.list());
			return "/supplier/listSuppliers";
		}
	}

	public String deleteSupplier(Map<String, Object> model, @RequestParam(value = "SupplierID") Long id) {
		try {
			if (id != null && id > 0) {
				suService.delete(id);
				model.put("message", "Se eliminó correctamente");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("message", "No se puede eliminar un proveedor");
		}
		model.put("listSuppliers", suService.list());
		return "";
	}

	@GetMapping("/")
	public String listSuppliers(Model model) {
		try {
			model.addAttribute("supplier", new Supplier());
			model.addAttribute("listSuppliers", suService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/supplier/listSuppliers";
	}

	@GetMapping("/")
	public String listSuppliersFind(Model model) {
		try {
			model.addAttribute("supplier", new Supplier());
			model.addAttribute("listSuppliers", suService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "supplier/find";
	}

	@RequestMapping("/")
	public String findBySupplier(Map<String, Object> model, @ModelAttribute Supplier supplier) throws ParseException {
		List<Supplier> listSuppliers;
		supplier.setName(supplier.getName());
		listSuppliers = suService.findByName(supplier.getName());
		if (listSuppliers.isEmpty()) {
			listSuppliers = suService.findByNameLikeIgnoreCase(supplier.getName());
		}
		if (listSuppliers.isEmpty()) {
			model.put("message", "No se encontró");
		}
		model.put("listCategories", listSuppliers);
		return "supplier/find";
	}
}
