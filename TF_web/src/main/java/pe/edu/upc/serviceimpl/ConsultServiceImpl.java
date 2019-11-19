package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.Consult;
import pe.edu.upc.repository.IConsultRepository;
import pe.edu.upc.service.IConsultService;

@Service
public class ConsultServiceImpl implements IConsultService {

	@Autowired
	private IConsultRepository consR;

	@Transactional(readOnly = true)
	@Override
	public List<Consult> list() {
		// TODO Auto-generated method stub
		return consR.findAll();
	}

	@Override
	public Optional<Consult> findById(long id) {
		// TODO Auto-generated method stub
		return consR.findById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Consult> fetchByConsultIdWithUserWhithRequiDetailsWithProduct(Long id) throws Exception {
		// TODO Auto-generated method stub
		return consR.fetchByConsultIdWithUserWhithRequiDetailsWithProduct(id);
	}

	@Override
	public List<String[]> listConsultType() {
		// TODO Auto-generated method stub
		return consR.consultType();
	}

}
