package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Accounting_Officer;

@Repository
public interface IAccounting_OfficerRepository extends JpaRepository<Accounting_Officer, Long>
{
	@Query("select count(a.Name) from Accounting_Officer a where a.Name =:Name")
	public int searchNameAccountingOfficer(@Param("Name") String Name);

	@Query("select a from Accounting_Officer a where a.Name like %:Name%")
	List<Accounting_Officer> findByName(String Name);

}
