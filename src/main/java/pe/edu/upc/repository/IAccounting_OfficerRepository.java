package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Accounting;

@Repository
public interface IAccounting_OfficerRepository extends JpaRepository<Accounting, Long>
{
	@Query("select count(a.DNI) from Accounting a where a.DNI =:DNI")
	public int searchDNIAccounting(@Param("DNI") String DNI);

	@Query("select a from Accounting a where a.Namex like %:Namex%")
	List<Accounting> findByNamex(String Name);
	
	@Query("select a from Accounting a")
	List<Accounting> findByListHello();

}
