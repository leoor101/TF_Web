package pe.edu.upc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "Product_Requirement")
public class Product_Requirement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productRequirementID;

	@NotEmpty(message = "Ingrese el nombre del requerimiento de producto")
	@Column(name = "name", nullable = false, length = 50)
	private String name;

	@NotEmpty(message = "Ingrese la descripción del producto")
	@Column(name = "description", nullable = false, length = 100)
	private String description;
	
	
	@Min(0)
	@Column(name = "price", nullable = false)
	private double price;


	public Long getProductRequirementID() {
		return productRequirementID;
	}


	public void setProductRequirementID(Long productRequirementID) {
		this.productRequirementID = productRequirementID;
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


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public Product_Requirement() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Product_Requirement(Long productRequirementID,
			@NotEmpty(message = "Ingrese el nombre del requerimiento de producto") String name,
			@NotEmpty(message = "Ingrese la descripción del producto") String description, @Min(0) double price) {
		super();
		this.productRequirementID = productRequirementID;
		this.name = name;
		this.description = description;
		this.price = price;
	}
	
	


	

}
