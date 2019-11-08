package pe.edu.upc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
	//@Autowired
	//private private BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping("/new")
	public String newUsers(Model model)
	{
		model.addAttribute("users",new Users());
		return "users/users";
	}
	@PostMapping("/save")
	public String saveCustomer(@Valid Users user_, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "users/users";
		} else {
			user_.setEnabled(true);
			uS.insert(user_);
			
			Users aux = new Users();
			aux.setUsername(user_.getUsername());
			aux.setPassword(user_.getPassword());
			aux.setEnabled(true);

		//	String bcryptPassword = passwordEncoder.encode(aux.getPassword());
		//	aux.setPassword(bcryptPassword);
		
			//aux = uS.BuscarPorNombre(user_.getUsername());

			uS.insRol("ROLE_USER", aux.getUserID());
		
			if (uS.insert(user_)==0) {
				return "redirect:/customers/list";
			} else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/customers/new";
			}
		}
	}
	
}
