package pe.edu.upc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upc.entity.Users;
import pe.edu.upc.serviceimpl.UsersServiceImpl;


@Controller
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private UsersServiceImpl uS;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Secured("ROLE_USER")
	@GetMapping("/new")
	public String newUsers(Model model)
	{
		model.addAttribute("user",new Users());
		return "user/user";
	}
	
	
	
	@PostMapping("/save")
	public String saveCustomer(@Valid Users user_, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "accounting/listAccounting";
		} else {
			user_.setEnabled(true);
			
			
			Users aux = new Users();
			aux.setUsername(user_.getUsername());
			aux.setPassword(user_.getPassword());
			aux.setEnabled(true);

			String bcryptPassword = passwordEncoder.encode(aux.getPassword());
			user_.setPassword(bcryptPassword);
			uS.insert(user_);
		
			aux = uS.BuscarPorNombre(user_.getUsername());

			uS.insRol("ROLE_USER", aux.getUserID());
		
			if (uS.insert(user_)==0) {
				return "redirect:/accountings/new";
			} else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/accountings/list";
			}
		}
	}
	
}
