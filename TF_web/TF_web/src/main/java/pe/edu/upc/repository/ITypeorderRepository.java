package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Order_Type;

@Repository
public interface ITypeorderRepository extends JpaRepository<Order_Type, Long> {

	@Query("select count(c.name) from Order_Type c where c.name =:name")
	public int findNameTypeOrder(@Param("name") String nameTypeOrder);

	@Query("select c from Order_Type c where c.name like %:name%")
	List<Order_Type> findByName(String name);

}
