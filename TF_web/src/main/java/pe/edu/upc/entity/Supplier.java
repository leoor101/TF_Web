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
	private Long SupplierID;

	@NotEmpty(message = "Ingrese el nombre del proveedor")
	@Column(name = "Name", nullable = false, length = 50)
	private String Name;

	@NotEmpty(message = "Ingrese el RUC del proveedor")
	@Column(name = "RUC", nullable = false, length = 11)
	private String RUC;

	@NotEmpty(message = "Ingrese el teléfono del proveedor")
	@Column(name = "PhoneNumber", nullable = false, length = 7)
	private String PhoneNumber;

	@NotEmpty(message = "Ingrese la dirección del proveedor")
	@Column(name = "Address", nullable = false, length = 50)
	private String Address;

	public Long getSupplierID() {
		return SupplierID;
	}

	public void setSupplierID(Long supplierID) {
		SupplierID = supplierID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getRUC() {
		return RUC;
	}

	public void setRUC(String rUC) {
		RUC = rUC;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public Supplier() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Supplier(Long supplierID, @NotEmpty(message = "Ingrese el nombre del proveedor") String name,
			@NotEmpty(message = "Ingrese el RUC del proveedor") String rUC,
			@NotEmpty(message = "Ingrese el teléfono del proveedor") String phoneNumber,
			@NotEmpty(message = "Ingrese la dirección del proveedor") String address) {
		super();
		SupplierID = supplierID;
		Name = name;
		RUC = rUC;
		PhoneNumber = phoneNumber;
		Address = address;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Address == null) ? 0 : Address.hashCode());
		result = prime * result + ((Name == null) ? 0 : Name.hashCode());
		result = prime * result + ((PhoneNumber == null) ? 0 : PhoneNumber.hashCode());
		result = prime * result + ((RUC == null) ? 0 : RUC.hashCode());
		result = prime * result + ((SupplierID == null) ? 0 : SupplierID.hashCode());
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
		Supplier other = (Supplier) obj;
		if (Address == null) {
			if (other.Address != null)
				return false;
		} else if (!Address.equals(other.Address))
			return false;
		if (Name == null) {
			if (other.Name != null)
				return false;
		} else if (!Name.equals(other.Name))
			return false;
		if (PhoneNumber == null) {
			if (other.PhoneNumber != null)
				return false;
		} else if (!PhoneNumber.equals(other.PhoneNumber))
			return false;
		if (RUC == null) {
			if (other.RUC != null)
				return false;
		} else if (!RUC.equals(other.RUC))
			return false;
		if (SupplierID == null) {
			if (other.SupplierID != null)
				return false;
		} else if (!SupplierID.equals(other.SupplierID))
			return false;
		return true;
	}

}
