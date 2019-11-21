package pe.edu.upc.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Stock;
@Repository
public interface IStockRepository extends JpaRepository<Stock, Long> {

	@Query("select o from Stock o join fetch o.product_requirement c join fetch o.stockDetails od join fetch od.product where o.id=?1")
	Optional<Stock> fetchByStockstockIDWithProduct_Requirement(Long id);

	List<Stock> findByCreateAt(Date fecha);

	@Query(value = "SELECT s.name, count(r.productRequirementID)from stock r join product_requirement s on s.supplierid = r.productRequirementID group by s.name", nativeQuery = true)
	List<String[]> stockedProducts();

	@Query(value = "SELECT s.description, count(r.productRequirementID)from stock r join product_requirement s on s.productRequirementID= r.productRequirementID group by s.description order by count(r.productRequirementID) desc limit 1", nativeQuery = true)
	List<String[]> stockedProductss();
	
	@Query(value = "SELECT s.price, count(r.productRequirementID)from stock r join product_requirement s on s.productRequirementID= r.productRequirementID group by s.price order by count(r.productRequirementID) desc limit 1", nativeQuery = true)
	List<String[]> stockedProductsss();
	
	

}
