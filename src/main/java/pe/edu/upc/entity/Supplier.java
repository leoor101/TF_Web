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
@Table(name = "Supplier")
public class Supplier {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long supplierID;

	@Size(min=1,max=50)
	@NotEmpty(message = "Ingrese el nombre del proveedor")
	@Column(name = "name", nullable = false, length = 50)
	private String name;
	
	@Size(min=11,max=11)
	@NotEmpty(message = "Ingrese el RUC del proveedor")
	@Column(name = "ruc", nullable = false, length = 11)
	private String ruc;

	@Size(min=7,max=9)
	@NotEmpty(message = "Ingrese el teléfono del proveedor")
	@Column(name = "phoneNumber", nullable = false, length = 9)
	private String phoneNumber;

	@Size(min=1,max=50)
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
