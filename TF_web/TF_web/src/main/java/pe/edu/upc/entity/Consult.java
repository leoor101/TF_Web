package pe.edu.upc.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Consult")
public class Consult {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long consultID;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_OrderID", nullable = false)
	private Order_Type type_OrderID;

	public Long getConsultID() {
		return consultID;
	}

	public void setConsultID(Long consultID) {
		this.consultID = consultID;
	}

	public Order_Type getType_OrderID() {
		return type_OrderID;
	}

	public void setType_OrderID(Order_Type type_OrderID) {
		this.type_OrderID = type_OrderID;
	}

}
