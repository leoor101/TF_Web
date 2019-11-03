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
	private Long ProductRequirementID;

	@NotEmpty(message = "Ingrese el nombre del requerimiento de producto")
	@Column(name = "Name", nullable = false, length = 50)
	private String Name;

	@NotEmpty(message = "Ingrese la descripción del producto")
	@Column(name = "Description", nullable = false, length = 100)
	private String Description;
	
	@NotEmpty(message = "Ingrese el precio del producto")
	@Min(0)
	@Column(name = "Price", nullable = false)
	private double Price;
	
	
	public double getPrice() {
		return Price;
	}

	public void setPrice(double price) {
		Price = price;
	}

	public Long getProductRequirementID() {
		return ProductRequirementID;
	}

	public void setProductRequirementID(Long productRequirementID) {
		ProductRequirementID = productRequirementID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public Product_Requirement() {
		super();
		// TODO Auto-generated constructor stub
	}



	

}
