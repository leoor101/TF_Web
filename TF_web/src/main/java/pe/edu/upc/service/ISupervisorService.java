package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Supervisor;

public interface ISupervisorService {
	public Integer insert(Supervisor supervisor);

	public void delete(long supervisorID);

	List<Supervisor> list();

	Optional<Supervisor> listarSupervisorID(long supervisorID);

	List<Supervisor> findByName(String name);

	Optional<Supervisor> findById(Long idSupervisor);

	/////////////
	Optional<Supervisor> fetchBySupervisorWithRequests(Long id);

}
