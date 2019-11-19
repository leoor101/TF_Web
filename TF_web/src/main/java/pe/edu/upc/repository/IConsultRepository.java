package pe.edu.upc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Consult;

@Repository
public interface IConsultRepository extends JpaRepository<Consult, Long> {

	@Query("select o from Consult o join fetch o.userId c join fetch o.requiDetails od join fetch od.product where o.consultID=?1")
	Optional<Consult> fetchByConsultIdWithUserWhithRequiDetailsWithProduct(Long requestID);

	@Query(value = "SELECT s.name, count(r.typeorderID)from consult r join supplier s on s.typeorderid = r.typeorderid group by s.name", nativeQuery = true)
	List<String[]> consultType();

}
