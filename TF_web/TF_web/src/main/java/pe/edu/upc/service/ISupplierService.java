package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Supplier;

public interface ISupplierService {
	public Integer insert(Supplier supplier);

	public void delete(long supplierID);
	
	List<Supplier> list();

	Optional<Supplier> listarId(long supplierID);

	List<Supplier> findByName(String name);

	public void insertmodified(Supplier supplier);
	}
