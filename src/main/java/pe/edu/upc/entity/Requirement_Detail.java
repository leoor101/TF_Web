package pe.edu.upc.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

@Entity
@Table(name = "Requirement_Detail")
public class Requirement_Detail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long requirementDetailID;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product_Requirement product;

	
	@Min(0)
	private int quantity;


	public Long getRequirementDetailID() {
		return requirementDetailID;
	}


	public void setRequirementDetailID(Long requirementDetailID) {
		this.requirementDetailID = requirementDetailID;
	}


	public Product_Requirement getProduct() {
		return product;
	}


	public void setProduct(Product_Requirement product) {
		this.product = product;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	public Double calculateAmount()
	{
		return quantity*product.getPrice();
	}

}
