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

import pe.edu.upc.entity.Product_Requirement;
import pe.edu.upc.entity.Request;
import pe.edu.upc.entity.Requirement_Detail;
import pe.edu.upc.entity.Supervisor;
import pe.edu.upc.service.IAccounting_OfficerService;
import pe.edu.upc.service.IProduct_RequirementService;
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
	private ISupervisorService superServ;
	@Autowired
	private ITypeorderService typeServ;
	@Autowired
	private IAccounting_OfficerService accServ;
	
	

	
	@GetMapping("/form/{id}")
	public String formRequest(@PathVariable(value = "id") Long id, Model model) {
		try {
			Optional<Supervisor> sup = superServ.findById(id);
			if (!sup.isPresent()) {
				model.addAttribute("info", "Cliente no existe");
				return "redirect:/supervisor/list";
			} else {
				Request a = new Request();
				a.setSupervisor(sup.get());
				model.addAttribute("request", a);
			}
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "request/form";
	}

	
	
	
	@PostMapping("/save")
	public String saveOrder(Request request, Model model, @RequestParam(name = "item_id[]", required = false) Long[] itemId,
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

		return "redirect:/supervisor/detail/" + request.getSupervisor().getSupervisorID();
	}

	
	
	
	
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

		return "request/detail";
	}

	
	
	
	
	@GetMapping("/listFind")
	public String listRequest(Model model) {
		try {
			model.addAttribute("request", new Request());
			model.addAttribute("listrequest", requestServ.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/request/find";
	}

	
	
	
	
	@RequestMapping("/find")
	public String findRequest(Map<String, Object> model, @ModelAttribute Request request) throws ParseException {

		List<Request> listrequest;
		request.setCreateAt(request.getCreateAt());
		listrequest = requestServ.findDate(request.getCreateAt());

		if (listrequest.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		model.put("listrequest", listrequest);
		return "request/find";

	}

	@GetMapping("/requestedSuppliers")
	public String listAllRequestedSuppliers(Map<String, Object> model) {
		model.put("requestedSuppliers", requestServ.listRequestedSuppliers());
		return "/request/requestedSuppliers";
	}
	
	
}
