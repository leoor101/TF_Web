package pe.edu.upc.controller;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.Request;
import pe.edu.upc.entity.Requirement_Detail;
import pe.edu.upc.entity.Supervisor;
import pe.edu.upc.service.IAccounting_OfficerService;
import pe.edu.upc.service.IProduct_RequirementService;
import pe.edu.upc.service.IRequestService;
import pe.edu.upc.service.ISupervisorService;
import pe.edu.upc.service.ISupplierService;
import pe.edu.upc.service.ITypeorderService;

@Controller
@SessionAttributes("request")
@RequestMapping("/requests")
public class RequestController {
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

	// registro de request por supervisor
	@GetMapping("/form/{id}")
	public String formRequest(@PathVariable(value = "id") Long id, Model model) {
		try {
			Optional<Supervisor> sup = superServ.findById(id);
			if (!sup.isPresent()) {
				model.addAttribute("info", "Supervisor no existe");
				return "redirect:/supervisor/list";
			} else {
				Request a = new Request();
				a.setSupervisor(sup.get());
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
	public String saveOrder(@Valid Request request, Model model, SessionStatus status, BindingResult binRes) {

		try {

			requestServ.insert(request);
			status.setComplete();
			model.addAttribute("mensaje", "Solicitud Generada");
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "redirect:/request/form";
		}

		return "redirect:/supervisor/listWith";
	}

	// un view de toda la orden/como se veria la orden;
	@GetMapping("/detailproduct/{id}")
	public String detailRequest(@PathVariable(value = "id") Long id, Map<String, Object> model,
			RedirectAttributes flash) {

		Request req = requestServ.listarId(id);
		if (req == null) {
			flash.addFlashAttribute("error", "El detalle no existe.");
			return "redirect:/supervisor/listWith";
		}
		model.put("request", req);
		model.put("titulo", "Detalle de Solicitud Id:" + req.getRequestID());
		
		return "request/detail/detailsList";
		
	}
	
	@RequestMapping("/newproduct/{id}")
	public String reNewProduct(@PathVariable(value = "id") Long id, Map<String, Object> model) {

		model.put("detail", new Requirement_Detail());
		model.put("listproduct", proServ.list());

		Request obj = requestServ.listarId(id);
		model.put("request", obj);

		return "request/detail/detailsForm";
	}
	
	@PostMapping("/saveproduct{id}")
	public String newProductRequest(@PathVariable(value = "id") long id, @Valid Requirement_Detail reqdetail,RedirectAttributes flash,
			BindingResult result, Model model, SessionStatus status) {
		Request request = requestServ.listarId(id);
		if(result.hasErrors())
		{
			flash.addFlashAttribute("error","El valor debe ser positivo");
			String cadena1 = "redirect:/requests/newproduct/" + id;
			return cadena1;
		}
		try {
			request.addrequiDetails(reqdetail);
			requestServ.insert(request);
			status.isComplete();
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			System.out.println(e.getMessage());
		}
		String cadena = "redirect:/requests/detailproduct/" + id;
		return cadena;
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
	
	
	
	

	@GetMapping("/requestedSuppliers")
	public String listAllRequestedSuppliers(Map<String, Object> model) {
		model.put("requestedSuppliers", requestServ.listRequestedSuppliers());
		return "request/requestedSuppliers";
	}

	@GetMapping("/requestedSupervisors")
	public String listAllRequestedSupervisors(Map<String, Object> model) {
		model.put("requestedSupervisors", requestServ.listRequestedSupervisors());
		return "request/requestedSupervisors";
	}

	@GetMapping("/requestAccounters")
	public String listAllRequestedAccounters(Map<String, Object> model) {
		model.put("requestedAccounters", requestServ.listRequestedAccounter());
		return "request/requestedAccounters";
	}
}
