package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Type_Order;

public interface ITypeorderService {

	public Integer insert(Type_Order type_order);
	public void delete (long idTypeOrder);
	public void modify(Type_Order type_order);
	
	List<Type_Order> list();
	Optional <Type_Order> listId(long idTypeOrder);
	
	List<Type_Order> findByName (String name);
	
	public void insertmodified(Type_Order tipo);
	
	
	
}
