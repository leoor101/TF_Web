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

import pe.edu.upc.entity.Accounting;
import pe.edu.upc.entity.Type_Order;
import pe.edu.upc.service.ITypeorderService;

@Controller
@RequestMapping("/ordertype")
public class TypeorderController {

	@Autowired
	private ITypeorderService caService;
	
	@RequestMapping("/index")
	public String irWelcome() {
		return "welcome";
	}
	
	@Secured("ROLE_USER")
	@GetMapping("/new")
	public String newordertype(Model model) {
		model.addAttribute("ordertype", new Type_Order());
		return "ordertype/ordertype";
	}
	@Secured("ROLE_USER")
	@PostMapping("/save")
	public String saveordertype(@Valid Type_Order type_order, BindingResult result, Model model, SessionStatus status)
	throws Exception{
		if(result.hasErrors()) {
			return "ordertype/ordertype";
		}else {
			int rpta= caService.insert(type_order);
			if(rpta>0) {
				model.addAttribute("mensaje", "Ya existe");
				return "/ordertype/ordertype";
			}else {
				model.addAttribute("mensaje","Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("listordertype", caService.list());
		return "/ordertype/ordertype";
	}
	@Secured("ROLE_USER")
	@GetMapping("/list")
	public String listTypesOrders(Model model) {
		try {
			model.addAttribute("type_order", new Type_Order());
			model.addAttribute("listTypesOrders", caService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/ordertype/listTypesOrders";
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
			model.put("mensaje", "No se pudo eliminar");
		}
		model.put("listTypesOrders", caService.list());


		return "/ordertype/listordertype";
	}
	@Secured("ROLE_USER")
	@GetMapping("/detalle/{id}")
	public String detailsordertype(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Type_Order> type_order = caService.listId(id);
			if (!type_order.isPresent()) {
				model.addAttribute("info", "El tipo de pedido no existe");
				return "redirect:/ordertype/list";
			} else {
				model.addAttribute("ordertype", type_order.get());
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/ordertype/update";
	}
	
	
	@Secured("ROLE_USER")
	@GetMapping("/listFind")
	public String listTypeOrderFind(Model model) {
		try {
			model.addAttribute("ordertype", new Type_Order());
			model.addAttribute("listordertype", caService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/ordertype/find";
	}
	@Secured("ROLE_USER")
	@PostMapping("/savemodify")
	public String saveCategory2(@Valid Type_Order tipoo, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "ordertype/ordertype";
		} else {
			caService.insertmodified(tipoo);

			model.addAttribute("ordertype", "Se modificó correctamente");
			model.addAttribute("listordertype", caService.list());
			status.setComplete();
			return "/ordertype/listordertype";
		}
	}
	
}
