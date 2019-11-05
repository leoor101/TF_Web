package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Product_Requirement;

@Repository
public interface IProduct_RequirementRepository extends JpaRepository<Product_Requirement, Long> {

	@Query("select count(a.Name) from Product_Requirement a where a.Name =:Name")
	public int searchNameProduct_Requirement(@Param("Name") String NameProdutc);

	@Query("select a from Product_Requirement a where a.Name like %:Name%")
	List<Product_Requirement> findByName(String Name);

	
}
