package pe.edu.upc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name= "tipos_pedidos")
public class Tipo_Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "Debe ingresar el nombre del pedido")
	@Column(name="nombre",nullable = false,length = 50)
	private String nombre;
	
	@NotEmpty(message = "Debe ingresar la descripción del producto")
	@Column(name="descripcion",nullable = false,length = 50)
	private String descripcion;
	
	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	
}
