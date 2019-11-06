package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Supplier;

@Repository
public interface ISupplierRepository extends JpaRepository<Supplier,Long> {
	@Query("select count(s.name) from Supplier s where s.name =:name")
	public int findSupplierName(@Param("name") String suppliername);

	@Query("select s from Supplier s where s.name like %:name%")
	List<Supplier> findByName(String name);

	
}
