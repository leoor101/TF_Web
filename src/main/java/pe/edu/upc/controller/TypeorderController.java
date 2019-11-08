package pe.edu.upc.controller;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upc.entity.Type_Order;
import pe.edu.upc.service.ITypeorderService;

@Controller
@RequestMapping("/Typeorders")
public class TypeorderController {

	@Autowired
	private ITypeorderService caService;
	
	@RequestMapping("/index")
	public String irWelcome() {
		return "welcome";
	}
	
	@Secured("ROLE_USER")
	@GetMapping("/new")
	public String newTypeorder(Model model) {
		model.addAttribute("typeorder", new Type_Order());
		return "typeorder/typeorder";
	}
	
	@PostMapping("/save")
	public String saveTypeorder(@Valid Type_Order type_order, BindingResult result, Model model, SessionStatus status)
	throws Exception{
		if(result.hasErrors()) {
			return "typeorder/typeorder";
		}else {
			int rpta= caService.insert(type_order);
			if(rpta>0) {
				model.addAttribute("mensaje", "Ya existe");
				return "/typeorder/typeorder";
			}else {
				model.addAttribute("mensaje","Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("listTypeOrder", caService.list());
		return "/typeorder/typeorder";
	}
	
	@GetMapping("/list")
	public String listTypesOrders(Model model) {
		try {
			model.addAttribute("type_order", new Type_Order());
			model.addAttribute("listTypesOrders", caService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/typeorder/listTypesOrders";
	}
	

	@RequestMapping("/delete")
	public String delete(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				caService.delete(id);
				model.put("mensaje", "Se eliminó correctamente");

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar una categoria");
		}
		model.put("listTypesOrders", caService.list());


		return "/typeorder/listTypesOrders";
	}
	@GetMapping("/detalle/{id}")
	public String detailsTypeOrder(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Type_Order> type_order = caService.listId(id);
			if (!type_order.isPresent()) {
				model.addAttribute("info", "Tipo de pedido no existe");
				return "redirect:/Typeorders/list";
			} else {
				model.addAttribute("type_order", type_order.get());
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/typeorder/update";
	}
	
	
	
	@GetMapping("/listFind")
	public String listTypeOrderFind(Model model) {
		try {
			model.addAttribute("type_order", new Type_Order());
			model.addAttribute("listTypesOrders", caService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/typeorder/find";
	}
	
	
}
