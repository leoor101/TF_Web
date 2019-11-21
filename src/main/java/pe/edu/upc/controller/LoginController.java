package pe.edu.upc.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.Role;
import pe.edu.upc.entity.Users;
import pe.edu.upc.service.IUsersService;

@Controller
@RequestMapping
public class LoginController {

	int value = 0;
	@Autowired
	private IUsersService uS;

	@GetMapping(value = { "/login", "/" })
	public String login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model, Principal principal,
			RedirectAttributes flash) {

		if (value == 0) {

			Role rol1 = new Role();
			rol1.setRol("ROLE_USER");
			Users pablaso = new Users();
			pablaso.setName("pablaso");
			pablaso.setUsername("web");
			pablaso.setEnabled(true);
			pablaso.setPassword("$2a$10$Z8w.VX1Sk6eldnWsyG.PR./lTXYHC4cjTarQpOEbpLnruqm.HK0Y2");
			pablaso.setPhoneNumber("9876543");
			pablaso.setEmailAddress("pablaso@gmail.com");
			pablaso.setRoles(new ArrayList<Role>());
			pablaso.addrol(rol1);
			uS.insert(pablaso);
			value++;
		}

		if (principal != null) {
			return "redirect:/accountings/index";
		}

		if (error != null) {
			model.addAttribute("error",
					"Error en el login: Nombre de usuario o contraseña incorrecta, por favor vuelva a intentarlo!");
		}

		if (logout != null) {
			model.addAttribute("success", "Ha cerrado sesión con éxito!");
		}

		return "login";
	}
}
