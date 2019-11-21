package pe.edu.upc.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class home {

	@RequestMapping("/mainn")
	public String goWelcome() {
		return "main";
	}
}

