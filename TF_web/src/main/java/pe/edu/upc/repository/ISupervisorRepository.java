package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Supervisor;


@Repository
public interface ISupervisorRepository  extends JpaRepository<Supervisor,Long>{
	@Query("select count(s.Name) from Supervisor s where s.Name =:Name")
	public int findSupervisorName(@Param("name") String supervisorName);

	@Query("select s from Supervisor s where s.Name like %:Name%")
	List<Supervisor> findByName(String name);

	List<Supervisor> findByNameLikeIgnoreCase(String name);
}
