package pe.edu.upc.repository;

import java.util.List;
import java.util.Optional;
import java.util.jar.Attributes.Name;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.edu.upc.entity.Consulttypeorder;

public interface IConsulttypeRepository extends JpaRepository<Consulttypeorder, Long> {

	@Query("select o from Type_Order o join fetch o.consultId c join fetch o.consultName od join fetch od.name where o.consulttypeorderID=?1")
	Optional<Consulttypeorder> fetchByIDWithUserWhithTypeOrderWithName(Long consulttypeorderID);

	List<Consulttypeorder> findByCreateAt(Name name);

}
