package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Supervisor;


public interface ISupervisorService {
	public Integer insert(Supervisor supervisor);

	public void delete(long supervisorID);
	
	List<Supervisor> list();

	Optional<Supervisor> listarId(long supervisorID);

	List<Supervisor> findByName(String name);

	List<Supervisor> findByNameLikeIgnoreCase(String name);
}