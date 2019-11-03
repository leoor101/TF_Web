package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Type_Order;

@Repository
public interface ITypeorderRepository extends JpaRepository<Type_Order, Long> {
	
	
	
	@Query("select count(c.name) from Type_Order c where c.name =:name")
	public int findNameTypeOrder(@Param("name") String nameTypeOrder);

	@Query("select c from Type_Order c where c.name like %:name%")
	List<Type_Order> findByName(String name);

}
