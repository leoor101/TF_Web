package pe.edu.upc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "Supplier")
public class Supplier {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long supplierID;

	@NotEmpty(message = "Ingrese el nombre del proveedor")
	@Column(name = "name", nullable = false, length = 50)
	private String name;
	
	
	@NotEmpty(message = "Ingrese el RUC del proveedor")
	@Column(name = "ruc", nullable = false, length = 11)
	private String ruc;

	@NotEmpty(message = "Ingrese el teléfono del proveedor")
	@Column(name = "phoneNumber", nullable = false, length = 7)
	private String phoneNumber;

	@NotEmpty(message = "Ingrese la dirección del proveedor")
	@Column(name = "address", nullable = false, length = 50)
	private String address;

	public Long getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(Long supplierID) {
		this.supplierID = supplierID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
	
	

}
