package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Product_Requirement;;

public interface IProduct_RequirementService {
	public Integer insert(Product_Requirement product);

	public void delete(long id);

	List<Product_Requirement> list();

	Optional<Product_Requirement> listProduct_RequirementId(long id);

	List<Product_Requirement> findByName(String name);

	public void insertmodified(Product_Requirement product);

}
