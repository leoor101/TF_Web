package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Stock;
@Repository
public interface IStockRepository extends JpaRepository<Stock, Long> {

	
	
	@Query("select count(s.name) from Stock s where s.name =:name")
	public int findStockName(@Param("name") String sotckname);

	@Query("select s from Stock s where s.name like %:name%")
	List<Stock> findByName(String name);


}
