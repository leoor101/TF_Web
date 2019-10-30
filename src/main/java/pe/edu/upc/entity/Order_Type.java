package pe.edu.upc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "Order_Type")
public class Order_Type {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int OrderTypeID;

	@NotEmpty(message = "Ingrese el nombre del tipo de orden")
	@Column(name = "OrderName", nullable = false, length = 50)
	private String OrderName;

	@NotEmpty(message = "Ingrese la descripción del tipo de orden")
	@Column(name = "Description", nullable = false, length = 100)
	private String Description;

	public int getOrderTypeID() {
		return OrderTypeID;
	}

	public void setOrderTypeID(int orderTypeID) {
		OrderTypeID = orderTypeID;
	}

	public String getOrderName() {
		return OrderName;
	}

	public void setOrderName(String orderName) {
		OrderName = orderName;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public Order_Type() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order_Type(int orderTypeID, @NotEmpty(message = "Ingrese el nombre del tipo de orden") String orderName,
			@NotEmpty(message = "Ingrese la descripción del tipo de orden") String description) {
		super();
		OrderTypeID = orderTypeID;
		OrderName = orderName;
		Description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Description == null) ? 0 : Description.hashCode());
		result = prime * result + ((OrderName == null) ? 0 : OrderName.hashCode());
		result = prime * result + OrderTypeID;
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
		Order_Type other = (Order_Type) obj;
		if (Description == null) {
			if (other.Description != null)
				return false;
		} else if (!Description.equals(other.Description))
			return false;
		if (OrderName == null) {
			if (other.OrderName != null)
				return false;
		} else if (!OrderName.equals(other.OrderName))
			return false;
		if (OrderTypeID != other.OrderTypeID)
			return false;
		return true;
	}

}
