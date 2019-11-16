package pe.edu.upc.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.edu.upc.entity.Consulttypeorder;
import pe.edu.upc.entity.Request;

public interface IConsulttypeRepository extends JpaRepository<Consulttypeorder, Long> {

	@Query("select o from Consulttypeorder o join fetch o.consultId c join fetch o.consultDescription od join fetch od.product where o.consulttypeorderID=?1")
	Optional<Consulttypeorder> fetchByType_OrderIdWithUserWhithconsultDescriptionWithProduct(Long requestID);

	List<Request> findByCreateAt(Id id);

}
