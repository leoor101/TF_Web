package pe.edu.upc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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

	public Product_Requirement(Long productRequirementID,
			@NotEmpty(message = "Ingrese el nombre del requerimiento de producto") String name,
			@NotEmpty(message = "Ingrese la descripción del producto") String description) {
		super();
		ProductRequirementID = productRequirementID;
		Name = name;
		Description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Description == null) ? 0 : Description.hashCode());
		result = prime * result + ((Name == null) ? 0 : Name.hashCode());
		result = prime * result + ((ProductRequirementID == null) ? 0 : ProductRequirementID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product_Requirement other = (Product_Requirement) obj;
		if (Description == null) {
			if (other.Description != null)
				return false;
		} else if (!Description.equals(other.Description))
			return false;
		if (Name == null) {
			if (other.Name != null)
				return false;
		} else if (!Name.equals(other.Name))
			return false;
		if (ProductRequirementID == null) {
			if (other.ProductRequirementID != null)
				return false;
		} else if (!ProductRequirementID.equals(other.ProductRequirementID))
			return false;
		return true;
	}

}
