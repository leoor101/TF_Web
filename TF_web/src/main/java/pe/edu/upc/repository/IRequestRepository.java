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

	@Query("select o from Request o join fetch o.userId c join fetch o.requiDetails od join fetch od.product where o.requestID=?1")
	Optional<Request> fetchByRequestIdWithUserWhithRequiDetailsWithProduct(Long requestID);

	List<Request> findByCreateAt(Date fecha);
	
}
