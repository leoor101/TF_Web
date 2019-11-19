package pe.edu.upc.serviceimpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.Request;
import pe.edu.upc.repository.IRequestRepository;
import pe.edu.upc.service.IRequestService;

@Service
public class RequestServiceImpl implements IRequestService {

	@Autowired
	private IRequestRepository reqR;
	
	
	@Transactional
	@Override
	public Request insert(Request request) {
		// TODO Auto-generated method stub
		return reqR.save(request);
	}
	@Transactional
	@Override
	public void delete(long idRequest) {
		reqR.deleteById(idRequest);
		
	}
	@Transactional(readOnly = true)
	@Override
	public List<Request> list() {
		// TODO Auto-generated method stub
		return reqR.findAll();
	}

	@Override
	public Optional<Request> findById(long id) {
		// TODO Auto-generated method stub
		return reqR.findById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Request> fetchByRequestIdWithUserWhithRequiDetailsWithProduct(Long id) throws Exception {
		// TODO Auto-generated method stub
		return reqR.fetchByRequestIdWithUserWhithRequiDetailsWithProduct(id);
	}

	@Override
	public List<Request> findDate(Date fecha) {
		// TODO Auto-generated method stub
		return reqR.findByCreateAt(fecha);
	}
	@Override
	public List<String[]> listRequestedSuppliers() {
		// TODO Auto-generated method stub
		return reqR.requestedSuppliers();

	}
	@Override
	public List<String[]> listRequestedSupervisors() {
		// TODO Auto-generated method stub
		return reqR.requestedSupervisors();
	}
	

}
