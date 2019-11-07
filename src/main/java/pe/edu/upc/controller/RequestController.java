package pe.edu.upc.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upc.entity.Customer;
import pe.edu.upc.entity.Order;
import pe.edu.upc.entity.OrderDetail;
import pe.edu.upc.entity.Product;
import pe.edu.upc.entity.Users;
import pe.edu.upc.service.IAccounting_OfficerService;
import pe.edu.upc.service.ICustomerService;
import pe.edu.upc.service.IOrderService;
import pe.edu.upc.service.IProductService;
import pe.edu.upc.service.IProduct_RequirementService;
import pe.edu.upc.service.IPurchase_FolderService;
import pe.edu.upc.service.IRequestService;
import pe.edu.upc.service.ISupervisorService;
import pe.edu.upc.service.ITypeorderService;
import pe.edu.upc.service.IUsersService;

@Controller
@SessionAttributes("request")
@RequestMapping("/requests")
public class RequestController 
{
	@Autowired
	private IRequestService requestServ;
	@Autowired
	private IUsersService userServ;
	@Autowired
	private IProduct_RequirementService proServ;
	@Autowired
	private IPurchase_FolderService purchaseServ;
	@Autowired
	private ISupervisorService superServ;
	@Autowired
	private ITypeorderService typeServ;
	@Autowired
	private IAccounting_OfficerService accServ;
	
	
	
	//falta todo primero hacer user
	
	@GetMapping("/form/{id}")
	public String formRequest(@PathVariable(value = "id") Long id, Model model) {
		try {
			Optional<Users> customer = userServ.find(id);
			if (!customer.isPresent()) {
				model.addAttribute("info", "Cliente no existe");
				return "redirect:/customers/list";
			} else {
				Order a = new Order();
				a.setCustomerId(customer.get());
				model.addAttribute("order", a);
			}
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "order/form";
	}

	
	
	
	@PostMapping("/save")
	public String saveOrder(Order order, Model model, @RequestParam(name = "item_id[]", required = false) Long[] itemId,
			@RequestParam(name = "quantity[]", required = false) Integer[] quantity, SessionStatus status) {
		try {

			if (itemId == null || itemId.length == 0) {
				model.addAttribute("info", "Orden no tiene productos");
				return "order/form";
			}

			for (int i = 0; i < itemId.length; i++) {
				Optional<Product> product = pS.findById(itemId[i]);
				if (product.isPresent()) {
					OrderDetail line = new OrderDetail();
					line.setQuantity(quantity[i]);
					line.setProductId(product.get());
					order.addDetailOrder(line);
				}
			}
			oS.insert(order);
			status.setComplete();
			model.addAttribute("success", "Orden Generada");
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}

		return "redirect:/customers/detail/" + order.getCustomerId().getId();
	}

	
	
	
	
	@GetMapping("/detail/{id}")
	public String detailOrder(@PathVariable(value = "id") Long id, Model model) {

		try {
			Optional<Order> order = oS.fetchByOrderIdWithCustomerWhithOrderDetailWithProduct(id);

			if (!order.isPresent()) {
				model.addAttribute("error", "Orden no existe");
				return "redirect:/customers/list";
			}

			model.addAttribute("order", order.get());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}

		return "order/detail";
	}

	
	
	
	
	@GetMapping("/listFind")
	public String listOrders(Model model) {
		try {
			model.addAttribute("order", new Order());
			model.addAttribute("listOrders", oS.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/order/find";
	}

	
	
	
	
	@RequestMapping("/find")
	public String findOrder(Map<String, Object> model, @ModelAttribute Order order) throws ParseException {

		List<Order> listOrders;
		order.setCreateAt(order.getCreateAt());
		listOrders = oS.buscarFecha(order.getCreateAt());

		if (listOrders.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		model.put("listOrders", listOrders);
		return "order/find";

	}

	
	
	
}
