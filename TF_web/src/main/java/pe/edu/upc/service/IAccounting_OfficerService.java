package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Accounting;;

public interface IAccounting_OfficerService 
{
	public Integer insert(Accounting acco);

	public void delete(long id);

	List<Accounting> list();

	Optional<Accounting> listarAccountingOfficerId(long id);

	List<Accounting> findByDNI(String DNI);
	
	public void insertmodified(Accounting accounting);
}
