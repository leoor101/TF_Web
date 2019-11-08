package pe.edu.upc.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import pe.edu.upc.entity.Supplier;
import pe.edu.upc.service.ISupplierService;

@Controller
@RequestMapping("/supplier")
public class SupplierController {

	@Autowired
	private ISupplierService suService;

	@RequestMapping("/index")
	public String irWelcome() {
		return "welcome";
	}

	@GetMapping("/new")
	public String newSupplier(Model model) {
		model.addAttribute("supplier", new Supplier());
		return "supplier/supplier";
	}
	@PostMapping("/save")
	public String saveSupplier(@Valid Supplier supplier, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "supplier/supplier";
		} else {
			int rpta = suService.insert(supplier);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "/supplier/supplier";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
			model.addAttribute("listSuppliers", suService.list());
			return "/supplier/listSuppliers";
		}
	}
	@RequestMapping("/delete")
	public String deleteSupplier(Map<String, Object> model, @RequestParam(value = "id") Long id) {
		try {
			if (id != null && id > 0) {
				suService.delete(id);
				model.put("mensaje", "Se eliminó correctamente");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar un proveedor");
		}
		model.put("listSuppliers", suService.list());
		return "/supplier/listSuppliers";
	}

	@GetMapping("/list")
	public String listSuppliers(Model model) {
		try {
			model.addAttribute("supplier", new Supplier());
			model.addAttribute("listSuppliers", suService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/supplier/listSuppliers";
	}

	@GetMapping("/listFind")
	public String listSuppliersFind(Model model) {
		try {
			model.addAttribute("supplier", new Supplier());
			model.addAttribute("listSuppliers", suService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "supplier/find";
	}

	@RequestMapping("/find")
	public String findBySupplier(Map<String, Object> model, @ModelAttribute Supplier supplier) throws ParseException {
		List<Supplier> listSuppliers;
		supplier.setName(supplier.getName());
		listSuppliers = suService.findByName(supplier.getName());
		
		if (listSuppliers.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		model.put("listSuppliers", listSuppliers);
		return "supplier/find";
	}
	
	@GetMapping("/detail/{id}")
	public String detailsSupplier(@PathVariable(value = "id") long id, Model model) {
		try {
			Optional<Supplier> supplier = suService.listarId(id);
			if (!supplier.isPresent()) {
				model.addAttribute("info", "El proveedor no existe");
				return "redirect:/supplier/list";
			} else {
				model.addAttribute("supplier", supplier.get());
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/supplier/update";
	}
	
	@PostMapping("/savemodify")
	public String saveSupplier2(@Valid Supplier sup, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "supplier/supplier";
		} else {
			suService.insertmodified(sup);

			model.addAttribute("mensaje", "Se modificó correctamente");
			model.addAttribute("listSuppliers", suService.list());
			status.setComplete();
			return "/supplier/listSuppliers";
		}
	}
}
