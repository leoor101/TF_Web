package pe.edu.upc.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class home {

	@RequestMapping("/")
	public String goWelcome() {
		return "main";
	}
}

