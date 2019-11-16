package pe.edu.upc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import pe.edu.upc.service.IAccounting_OfficerService;
import pe.edu.upc.service.IConsulttypeService;
import pe.edu.upc.service.ITypeorderService;

@Controller
@SessionAttributes("consult")
@RequestMapping("/consults")
public class ConsulttypeorderController {

	@Autowired
	private IConsulttypeService consultServ;
	@Autowired
	private ITypeorderService typeServ;
	@Autowired
	private IAccounting_OfficerService accServ;

}
