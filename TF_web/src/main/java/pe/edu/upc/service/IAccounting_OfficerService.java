package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Accounting_Officer;;

public interface IAccounting_OfficerService 
{
	public Integer insert(Accounting_Officer acco);

	public void delete(long id);

	List<Accounting_Officer> list();

	Optional<Accounting_Officer> listarAccountingOfficerId(long id);

	List<Accounting_Officer> findByName(String Name);
}
