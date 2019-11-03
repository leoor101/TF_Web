package pe.edu.upc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "Accounting_Officer")
public class Accounting_Officer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long AccountingOfficerID;

	@NotEmpty(message = "Ingrese el nombre del encargado")
	@Column(name = "Name", nullable = false, length = 50)
	private String Name;

	@NotEmpty(message = "Ingrese el DNI del encargado")
	@Column(name = "DNI", nullable = false, length = 7)
	private String DNI;

	public Long getAccountingOfficerID() {
		return AccountingOfficerID;
	}

	public void setAccountingOfficerID(Long accountingOfficerID) {
		AccountingOfficerID = accountingOfficerID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public Accounting_Officer() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Accounting_Officer(Long accountingOfficerID,
			@NotEmpty(message = "Ingrese el nombre del encargado") String name,
			@NotEmpty(message = "Ingrese el DNI del encargado") String dNI) {

		super();
		AccountingOfficerID = accountingOfficerID;
		Name = name;
		DNI = dNI;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((AccountingOfficerID == null) ? 0 : AccountingOfficerID.hashCode());
		result = prime * result + ((DNI == null) ? 0 : DNI.hashCode());
		result = prime * result + ((Name == null) ? 0 : Name.hashCode());
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
		Accounting_Officer other = (Accounting_Officer) obj;
		if (AccountingOfficerID == null) {
			if (other.AccountingOfficerID != null)
				return false;
		} else if (!AccountingOfficerID.equals(other.AccountingOfficerID))
			return false;
		if (DNI == null) {
			if (other.DNI != null)
				return false;
		} else if (!DNI.equals(other.DNI))
			return false;
		if (Name == null) {
			if (other.Name != null)
				return false;
		} else if (!Name.equals(other.Name))
			return false;
		return true;
	}

}
