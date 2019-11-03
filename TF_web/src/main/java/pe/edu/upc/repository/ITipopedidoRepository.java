package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.edu.upc.entity.Tipo_Pedido;

public interface ITipopedidoRepository extends JpaRepository<Tipo_Pedido, Long> {
	@Query("select count(c.nombre) from Tipo_Pedido c where c.nombre =:nombre")
	public int buscarNombreTipoProducto(@Param("nombre") String nombreTipoproducto);

	@Query("select c from Tipo_Pedido c where c.nombre like %:nombre%")
	List<Tipo_Pedido> findByName(String nombre);

	List<Tipo_Pedido> findByNameLikeIgnoreCase(String nombre);

}
