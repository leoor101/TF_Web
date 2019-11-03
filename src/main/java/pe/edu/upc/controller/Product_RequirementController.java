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
import pe.edu.upc.entity.Product_Requirement;
import pe.edu.upc.service.IProduct_RequirementService;

@Controller
@RequestMapping("/products")
public class Product_RequirementController 
{
	@Autowired
	private IProduct_RequirementService proService;
	
	@RequestMapping("/index")
	public String goWelcome() {
		return "welcome";
	}

	@GetMapping("/new")
	public String newProduct_Requirement(Model model) {
		model.addAttribute("product", new Product_Requirement());
		return "product_requirement/product";
		
	}

	@PostMapping("/save")
	public String saveProduct_Requirement(@Valid Product_Requirement product, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "product_requirement/product";
		} else {
			int rpta = proService.insert(product);
			if (rpta > 0) {
				model.addAttribute("mensaje", "It alredy exists");
				return "product_requirement/product";
			} else {
				model.addAttribute("mensaje", "It is saved succesfully");
				status.setComplete();
			}
		}
		model.addAttribute("listproduct", proService.list());

		return "product_requirement/product";
	}

	@GetMapping("/list")
	public String listProduct_Requirement(Model model) {
		try {
			model.addAttribute("product", new Product_Requirement());
			model.addAttribute("listproduct", proService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/product_requirement/listproduct";
	}

	@RequestMapping("/delete")
	public String delete(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				proService.delete(id);
				model.put("mensaje", "It has been deleted succesfully");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "It could not be saved");
		}
		model.put("listproduct", proService.list());

		return "/product_requirement/listproduct";

	}

	@GetMapping("/detalle/{id}")
	public String detailsProduct(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Product_Requirement> product = proService.listProduct_RequirementId(id);
			if (!product.isPresent()) {
				model.addAttribute("info", "Product doesn't exist");
				return "redirect:/products/list";
			} else {
				model.addAttribute("product_requirement", product.get());
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/product_requirement/update";
	}

	
	@GetMapping("/listFind")
	public String listProduct_RequirementFind(Model model) {
		try {
			model.addAttribute("product", new Product_Requirement());
			model.addAttribute("listproduct", proService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/product_requirement/find";
	}
	@RequestMapping("/find")
	public String findByProduct_Requirement(Map<String, Object> model, @ModelAttribute Product_Requirement product) throws ParseException {

		List<Product_Requirement> listproducts;
		product.setName(product.getName());
		listproducts = proService.findByName(product.getName());

		if (listproducts.isEmpty()) {
			model.put("mensaje", "It is not found");
		}
		model.put("listproduct", listproducts);
		return "product_requirement/find";

	}
}
