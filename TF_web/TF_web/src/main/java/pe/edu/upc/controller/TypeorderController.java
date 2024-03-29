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
import pe.edu.upc.entity.Order_Type;
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
		model.addAttribute("ordertype", new Order_Type());
		return "ordertype/ordertype";
	}
	@Secured("ROLE_USER")
	@PostMapping("/save")
	public String saveordertype(@Valid Order_Type type_order, BindingResult result, Model model, SessionStatus status)
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
		return "/ordertype/listordertype";
	}
	
	@GetMapping("/list")
	public String listordertype(Model model) {
		try {
			model.addAttribute("type_order", new Order_Type());
			model.addAttribute("listordertype", caService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/ordertype/listordertype";
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
		model.put("listordertype", caService.list());


		return "/ordertype/listordertype";
	}
	@Secured("ROLE_USER")
	@GetMapping("/detail/{id}")
	public String detailsordertype(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Order_Type> type_order = caService.listId(id);
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
	public String listordertypeFind(Model model) {
		try {
			model.addAttribute("ordertype", new Order_Type());
			model.addAttribute("listordertype", caService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/ordertype/find";
	}
	@Secured("ROLE_USER")
	@PostMapping("/savemodify")
	public String saveOrderType2(@Valid Order_Type tipoo, BindingResult result, Model model, SessionStatus status)
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
	
	@Secured("ROLE_USER")
	@RequestMapping("/find")
	public String findordertype(Map<String, Object> model, @ModelAttribute Order_Type ordertype) throws ParseException {

		List<Order_Type> listordertype;
		ordertype.setName(ordertype.getName());
		listordertype = caService.findByName(ordertype.getName());

		if (listordertype.isEmpty()) {
			model.put("mensaje", "No se pudo encontrar");
		}
		model.put("listordertype", listordertype);
		return "ordertype/find";

	}
	
	
	
	
}
