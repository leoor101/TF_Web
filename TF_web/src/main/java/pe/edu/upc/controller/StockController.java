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

import pe.edu.upc.entity.Stock;
import pe.edu.upc.entity.Supplier;
import pe.edu.upc.service.IStockService;

@Controller
@RequestMapping("/stock")
public class StockController {

	@Autowired
	private IStockService stService;

	@RequestMapping("/index")
	public String irWelcome() {
		return "welcome";
	}

	@Secured("ROLE_USER")
	@GetMapping("/new")
	public String newStock(Model model) {
		model.addAttribute("stock", new Stock());
		return "stock/stock";
	}

	@Secured("ROLE_USER")
	@PostMapping("/save")
	public String saveStock(@Valid Stock stock, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "stock/stock";
		} else {
			int rpta = stService.insert(stock);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "/stock/stock";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
			model.addAttribute("listStockss", stService.list());
			return "/stock/listStockss";
		}
	}

	@Secured("ROLE_USER")
	@RequestMapping("/delete")
	public String deleteStock(Map<String, Object> model, @RequestParam(value = "id") Long id) {
		try {
			if (id != null && id > 0) {
				stService.delete(id);
				model.put("mensaje", "Se eliminó correctamente");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se pudo eliminar");
		}
		model.put("listStockss", stService.list());
		return "/stock/listStockss";
	}

	@Secured("ROLE_USER")
	@GetMapping("/list")
	public String listStockss(Model model) {
		try {
			model.addAttribute("stock", new Supplier());
			model.addAttribute("listStockss", stService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/stock/listStockss";
	}

	@Secured("ROLE_USER")
	@GetMapping("/listFind")
	public String listStockssFind(Model model) {
		try {
			model.addAttribute("stock", new Supplier());
			model.addAttribute("listStockss", stService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "stock/find";
	}

	@Secured("ROLE_USER")
	@RequestMapping("/find")
	public String findByStock(Map<String, Object> model, @ModelAttribute Stock stock) throws ParseException {
		List<Stock> listStockss;
		stock.setName(stock.getName());
		listStockss = stService.findByName(stock.getName());

		if (listStockss.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		model.put("listStockss", listStockss);
		return "stock/find";
	}

	@Secured("ROLE_USER")
	@GetMapping("/detail/{id}")
	public String detailsStock(@PathVariable(value = "id") long id, Model model) {
		try {
			Optional<Stock> stock = stService.listarId(id);
			if (!stock.isPresent()) {
				model.addAttribute("info", "El tipo de orden no existe");
				return "redirect:/stock/list";
			} else {
				model.addAttribute("stock", stock.get());
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/stock/update";
	}

	@Secured("ROLE_USER")
	@PostMapping("/savemodify")
	public String saveStock2(@Valid Stock sup, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "stock/stock";
		} else {
			stService.insertmodified(sup);

			model.addAttribute("mensaje", "Se modificó correctamente");
			model.addAttribute("listStockss", stService.list());
			status.setComplete();
			return "/stock/listStockss";
		}
	}

}
