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

import pe.edu.upc.entity.Product_Requirement;
import pe.edu.upc.service.IProduct_RequirementService;

@Controller
@RequestMapping("/product")
public class Product_RequirementController 
{
	@Autowired
	private IProduct_RequirementService proService;
	
	@RequestMapping("/index")
	public String goWelcome() {
		return "welcome";
	}
	@Secured("ROLE_USER")
	@GetMapping("/new")
	public String newProduct_Requirement(Model model) {
		model.addAttribute("product", new Product_Requirement());
		return "product/product";		
	}
	@Secured("ROLE_USER")
	@PostMapping("/save")
	public String saveProduct_Requirement(@Valid Product_Requirement product, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "product/product";
		} else {
			int rpta = proService.insert(product);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "/product/product";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("listproduct", proService.list());

		return "/product/listproduct";
	}
	@Secured("ROLE_USER")
	@GetMapping("/list")
	public String listproduct(Model model) {
		try {
			model.addAttribute("product", new Product_Requirement());
			model.addAttribute("listproduct", proService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/product/listproduct";
	}
	@Secured("ROLE_USER")
	@RequestMapping("/delete")
	public String delete(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				proService.delete(id);
				model.put("mensaje", "Se eliminó correctamente");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se pudo eliminar");
		}
		model.put("listproduct", proService.list());

		return "/product/listproduct";

	}
	@Secured("ROLE_USER")
	@GetMapping("/listFind")
	public String listProduct_RequirementFind(Model model) {
		try {
			model.addAttribute("product", new Product_Requirement());
			model.addAttribute("listproduct", proService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/product/find";
	}
	@Secured("ROLE_USER")
	@RequestMapping("/find")
	public String findByProduct_Requirement(Map<String, Object> model, @ModelAttribute Product_Requirement product) throws ParseException {

		List<Product_Requirement> listproduct;
		product.setName(product.getName());
		listproduct = proService.findByName(product.getName());

		if (listproduct.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		model.put("listproduct", listproduct);
		model.put("product", new Product_Requirement());
		return "product/find";

	}
	
	
	@GetMapping("/detail/{id}")
	public String detailsProduct(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Product_Requirement> product = proService.listProduct_RequirementId(id);
			if (!product.isPresent()) {
				model.addAttribute("info", "El producto no existe");
				return "redirect:/product/list";
			} else {
				model.addAttribute("product", product.get());
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/product/update";
	}
	@PostMapping("/savemodify")
	public String saveProduct2(@Valid Product_Requirement prod, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "product/product";
		} else {
			proService.insertmodified(prod);

			model.addAttribute("mensaje", "Se modificó correctamente");
			model.addAttribute("listproduct", proService.list());
			status.setComplete();
			return "/product/listproduct";
		}
	}
}
