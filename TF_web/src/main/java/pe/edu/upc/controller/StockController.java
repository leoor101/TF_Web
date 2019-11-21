package pe.edu.upc.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upc.entity.Product_Requirement;
import pe.edu.upc.entity.Request;
import pe.edu.upc.entity.Requirement_Detail;
import pe.edu.upc.service.IAccounting_OfficerService;
import pe.edu.upc.service.IProduct_RequirementService;
import pe.edu.upc.service.IRequestService;
import pe.edu.upc.service.ISupervisorService;
import pe.edu.upc.service.ISupplierService;
import pe.edu.upc.service.ITypeorderService;

@Controller
@SessionAttributes("stocks")
@RequestMapping("/stocks")
public class StockController {

	@Autowired
	private IRequestService requestServ;
	@Autowired
	private IProduct_RequirementService proServ;
	@Autowired
	private ISupervisorService superServ;
	@Autowired
	private ITypeorderService typeServ;
	@Autowired
	private IAccounting_OfficerService accServ;
	@Autowired
	private ISupplierService supplierServ;

	@GetMapping("/form/{id}")
	public String formStock(@PathVariable(value = "id") Long id, Model model) {
		try {
			Optional<Product_Requirement> pro = proServ.listProduct_RequirementId(id);
			if (!pro.isPresent()) {
				model.addAttribute("info", "Producto no existe");
				return "redirect:/product/list";
			} else {
				Request a = new Request();
				a.(pro.get());
				model.addAttribute("request", a);
				model.addAttribute("listSuppliers", supplierServ.list());
				model.addAttribute("listordertype", typeServ.list());
				model.addAttribute("listAccounting", accServ.list());
				
			}
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "request/form";
	}

	@PostMapping("/save")
	public String saveOrder(Request request, Model model,
			@RequestParam(name = "item_id[]", required = false) Long[] itemId,
			@RequestParam(name = "quantity[]", required = false) Integer[] quantity, SessionStatus status) {
		try {

			if (itemId == null || itemId.length == 0) {
				model.addAttribute("info", "La orden no tiene productos");
				return "request/form";
			}

			for (int i = 0; i < itemId.length; i++) {
				Optional<Product_Requirement> product = proServ.listProduct_RequirementId(itemId[i]);
				if (product.isPresent()) {
					Requirement_Detail line = new Requirement_Detail();
					line.setQuantity(quantity[i]);
					line.setProduct(product.get());
					request.addrequiDetails(line);
				}
			}
			requestServ.insert(request);
			status.setComplete();
			model.addAttribute("success", "Solicitud generada");
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}

		return "request/listrequest"; //deberia de regresar al supervisor Details
	}

	
	// un view de toda la orden/como se veria la orden;
	@GetMapping("/detail/{id}")
	public String detailRequest(@PathVariable(value = "id") Long id, Model model) {

		try {
			Optional<Request> request = requestServ.fetchByRequestIdWithUserWhithRequiDetailsWithProduct(id);

			if (!request.isPresent()) {
				model.addAttribute("error", "La solicitud no existe");
				return "redirect:/supervisor/list";
			}
			model.addAttribute("request", request.get());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}

		return "request/detailsProduct";
	}

	/*
	 * para buscar por fecha
	 * 
	 * @GetMapping("/listFind") public String listRequest(Model model) { try {
	 * model.addAttribute("request", new Request());
	 * model.addAttribute("listrequest", requestServ.list()); } catch (Exception e)
	 * { model.addAttribute("error", e.getMessage()); } return "/request/find"; }
	 * 
	 * 
	 * @RequestMapping("/find") public String findRequest(Map<String, Object>
	 * model, @ModelAttribute Request request) throws ParseException {
	 * 
	 * List<Request> listrequest; request.setCreateAt(request.getCreateAt());
	 * listrequest = requestServ.findDate(request.getCreateAt());
	 * 
	 * if (listrequest.isEmpty()) { model.put("mensaje", "No se encontró"); }
	 * model.put("listrequest", listrequest); return "request/find";
	 * 
	 * }
	 */
	
	
	
	// registro de request por supervisor
	@GetMapping("/requestedSuppliers")
	public String listAllRequestedSuppliers(Map<String, Object> model) {
		model.put("requestedSuppliers", requestServ.listRequestedSuppliers());
		return "/request/requestedSuppliers";
	}

	@GetMapping("/requestedSupervisors")
	public String listAllRequestedSupervisors(Map<String, Object> model) {
		model.put("requestedSupervisors", requestServ.listRequestedSupervisors());
		return "/request/requestedSupervisors";
	}

}
