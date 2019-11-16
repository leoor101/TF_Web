package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.jar.Attributes.Name;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.Consulttypeorder;
import pe.edu.upc.repository.IConsulttypeRepository;
import pe.edu.upc.service.IConsulttypeService;

@Service
public class ConsulttypeorderServiceImpl implements IConsulttypeService {

	@Autowired
	private IConsulttypeRepository reqR;

	@Transactional(readOnly = true)
	@Override
	public List<Consulttypeorder> list() {
		// TODO Auto-generated method stub
		return reqR.findAll();
	}

	@Override
	public Optional<Consulttypeorder> findById(long id) {
		// TODO Auto-generated method stub
		return reqR.findById(id);
	}
	
	@Transactional(readOnly = true)
	@Override
	public Optional<Consulttypeorder> fetchByIDWithUserWhithTypeOrderWithName(Long id) throws Exception {
		// TODO Auto-generated method stub
		return reqR.fetchByIDWithUserWhithTypeOrderWithName(id);
	}

	@Override
	public List<Consulttypeorder> findName(Name name) {
		// TODO Auto-generated method stub
		return reqR.findByCreateAt(name);
	}

}
