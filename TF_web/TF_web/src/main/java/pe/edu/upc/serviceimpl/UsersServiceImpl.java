package pe.edu.upc.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.Users;
import pe.edu.upc.repository.IUsersRepository;
import pe.edu.upc.service.IUsersService;

@Service
public class UsersServiceImpl implements IUsersService {

	@Autowired
	private IUsersRepository uR;
	@Transactional
	@Override
	public void insRol(String rol, Long user_id) {
		uR.insRol(rol, user_id);
	}
	@Transactional
	@Override
	public Integer insert(Users user) {
		int rpta = uR.findUsername(user.getUsername());
		if (rpta == 0) {
			uR.save(user);
		}
		return rpta;
	}
	
	
	@Override
	public Users BuscarPorNombre(String username) {
		// TODO Auto-generated method stub
		return uR.findname(username);
	}

	

}
