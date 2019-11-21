package pe.edu.upc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name= "order_type")
public class Order_Type {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min=1,max=50)
	@NotEmpty(message = "Debe ingresar el nombre del pedido")
	@Column(name="name  ",nullable = false,length = 50)
	private String name;
	
	@Size(min=1,max=50)
	@NotEmpty(message = "Debe ingresar la descripción del producto")
	@Column(name="description",nullable = false,length = 50)
	private String description;
	
	
	
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	
}
