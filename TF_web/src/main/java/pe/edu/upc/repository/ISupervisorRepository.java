package pe.edu.upc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Supervisor;


@Repository
public interface ISupervisorRepository  extends JpaRepository<Supervisor,Long>{
	@Query("select count(s.name) from Supervisor s where s.name =:name")
	public int findSupervisorName(@Param("name") String supervisorname);

	@Query("select s from Supervisor s where s.name like %:name%")
	List<Supervisor> findByName(String name);


	////////////////////
	@Query("SELECT s FROM Supervisor s LEFT JOIN FETCH s.requests o where s.supervisorID=?1")
	Optional<Supervisor> fetchBySupervisorWithRequests(Long id);
}
