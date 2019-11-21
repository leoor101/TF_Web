package pe.edu.upc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "Stock")
public class Stock {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long stockID;

	@NotEmpty(message = "Ingrese el nombre del tipo del producto")
	@Column(name = "name", nullable = false, length = 50)
	private String name;

	public Long getStockID() {
		return stockID;
	}

	public void setStockID(Long stockID) {
		this.stockID = stockID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
