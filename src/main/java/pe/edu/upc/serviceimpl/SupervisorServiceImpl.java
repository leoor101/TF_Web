package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.Supervisor;
import pe.edu.upc.repository.ISupervisorRepository;
import pe.edu.upc.service.ISupervisorService;

@Service
public class SupervisorServiceImpl implements ISupervisorService {

	@Autowired
	private ISupervisorRepository sR;

	@Override
	@Transactional
	public Integer insert(Supervisor supervisor) {
		int rpta = sR.findSupervisorName(supervisor.getName());
		if (rpta == 0) {
			sR.save(supervisor);
		}
		return rpta;
	}

	@Override
	@Transactional
	public void delete(long supervisorID) {
		sR.deleteById(supervisorID);

	}

	@Override
	@Transactional(readOnly = true)
	public List<Supervisor> list() {
		return sR.findAll(Sort.by(Sort.Direction.DESC, "name"));
	}

	@Override
	public Optional<Supervisor> listarSupervisorID(long supervisorID) {
		return sR.findById(supervisorID);
	}

	@Override
	public List<Supervisor> findByName(String name) {
		return sR.findByName(name);
	}

	@Override
	public Optional<Supervisor> findById(Long idSupervisor) {
		// TODO Auto-generated method stub
		return sR.findById(idSupervisor);
	}

	@Override
	public Optional<Supervisor> fetchBySupervisorWithRequests(Long id) {
		// TODO Auto-generated method stub
		return sR.fetchBySupervisorWithRequests(id);
	}

	@Override
	public void insertmodified(Supervisor supervisor) {
		sR.save(supervisor);
		
	}

}
