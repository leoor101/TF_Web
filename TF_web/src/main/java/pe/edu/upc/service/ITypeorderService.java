package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Order_Type;

public interface ITypeorderService {

	public Integer insert(Order_Type type_order);
	public void delete (long idTypeOrder);
	public void modify(Order_Type type_order);
	
	List<Order_Type> list();
	Optional <Order_Type> listId(long idTypeOrder);
	
	List<Order_Type> findByName (String name);
	
	public void insertmodified(Order_Type tipo);
	
	
	
}
