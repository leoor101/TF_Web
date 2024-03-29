package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Product_Requirement;

@Repository
public interface IProduct_RequirementRepository extends JpaRepository<Product_Requirement, Long> {

	@Query("select count(c.name) from Product_Requirement c where c.name =:name")
	public int searchNameProduct_Requirement(@Param("name") String name);

	@Query("select c from Product_Requirement c where c.name like %:name%")
		List<Product_Requirement> findByName(String name);

	
}
