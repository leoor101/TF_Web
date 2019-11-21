package pe.edu.upc.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Stock")
public class Stock {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long stockID;

	@Column(name = "CreationDate")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createAt;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "description_ID")
	private List<Product_Requirement> stockDetails;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "name_ID", nullable = false)
	private Product_Requirement name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "precio_ID", nullable = false)
	private Product_Requirement precioID;

	public Long getStockID() {
		return stockID;
	}

	public void setStockID(Long stockID) {
		this.stockID = stockID;
	}

	public List<Product_Requirement> getStockDetails() {
		return stockDetails;
	}

	public void setStockDetails(List<Product_Requirement> stockDetails) {
		this.stockDetails = stockDetails;
	}

	public Product_Requirement getName() {
		return name;
	}

	public void setName(Product_Requirement name) {
		this.name = name;
	}

	public Product_Requirement getPrecioID() {
		return precioID;
	}

	public void setPrecioID(Product_Requirement precioID) {
		this.precioID = precioID;
	}

}
