package pe.edu.upc.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Request;

@Repository
public interface IRequestRepository extends JpaRepository<Request, Long> {

	
	@Query("select o from Request o join fetch o.supervisor c join fetch o.requiDetails od join fetch od.product where o.requestID=?1")
	Optional<Request> fetchByRequestrequestIDWithSupervisorWhithRequirement_DetailWithProduct_Requirement(Long id);
	
	List<Request> findByCreateAt(Date fecha);

	@Query(value = "SELECT s.name, count(r.supplierid)from request r join supplier s on s.supplierid = r.supplierid group by s.name", nativeQuery = true)
	List<String[]> requestedSuppliers();

	@Query(value = "SELECT s.name, count(r.supervisorID)from request r join supervisor s on s.supervisorID= r.supervisorID group by s.name order by count(r.supervisorID) desc limit 1", nativeQuery = true)
	List<String[]> requestedSupervisors();
	
	@Query(value =  "SELECT a.Namex, count(r.accountingID) from request r join accounting a on a.Accounting_OfficerID = r.accountingID group by a.Namex order by count(r.accountingID) desc limit 1", nativeQuery = true)
	List<String[]> requestedAccounter();
	
	
}
