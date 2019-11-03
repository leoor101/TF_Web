package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Supplier;

@Repository
public interface ISupplierRepository extends JpaRepository<Supplier,Long> {
	@Query("select count(s.Name) from Supplier s where s.Name =:Name")
	public int findSupplierName(@Param("Name") String supplierName);

	@Query("select s from Supplier s where s.Name like %:Name%")
	List<Supplier> findByName(String Name);

	
}
