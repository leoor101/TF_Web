package pe.edu.upc.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upc.entity.Product_Requirement;
import pe.edu.upc.entity.Purchase_Folder;
import pe.edu.upc.service.IProduct_RequirementService;
import pe.edu.upc.service.IPurchase_FolderService;

@Controller
@RequestMapping("/purchasefolder")
public class Purchase_FolderController 
{
	@Autowired
	private IPurchase_FolderService pfService;
	
	@RequestMapping("/index")
	public String goWelcome() {
		return "welcome";
	}

	@GetMapping("/new")
	public String newPurchase_Folder(Model model) {
		model.addAttribute("purchasefolder", new Purchase_Folder());
		return "purchasefolder/purchasefolder";		
	}

	@PostMapping("/save")
	public String savePurchase_Folder(@Valid Purchase_Folder purchase, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "purchasefolder/purchasefolder";
		} else {
			int rpta = pfService.insert(purchase);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "/purchasefolder/purchasefolder";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("listpurchasefolder", pfService.list());

		return "/purchasefolder/listpurchasefolder";
	}

	@GetMapping("/list")
	public String listpurchasefolder(Model model) {
		try {
			model.addAttribute("purchasefolder", new Purchase_Folder());
			model.addAttribute("listpurchasefolder", pfService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/purchasefolder/listpurchasefolder";
	}

	@RequestMapping("/delete")
	public String delete(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				pfService.delete(id);
				model.put("mensaje", "It has been deleted succesfully");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "It could not be saved");
		}
		model.put("listpurchasefolder", pfService.list());

		return "/purchasefolder/listpurchasefolder";

	}
	
	@GetMapping("/listFind")
	public String listPurchase_FolderFind(Model model) {
		try {
			model.addAttribute("purchasefolder", new Purchase_Folder());
			model.addAttribute("listpurchasefolder", pfService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/purchasefolder/find";
	}
	@RequestMapping("/find")
	public String findByPurchase_Folder(Map<String, Object> model, @ModelAttribute Purchase_Folder purchase) throws ParseException {

		
		List<Purchase_Folder> listpurchase;
		purchase.setFolderName(purchase.getFolderName());
		listpurchase = pfService.findByName(purchase.getFolderName());

		if (listpurchase.isEmpty()) {
			model.put("mensaje", "It is not found");
		}
		model.put("listpurchasefolder", listpurchase);
		return "purchasefolder/find";

	}
}
