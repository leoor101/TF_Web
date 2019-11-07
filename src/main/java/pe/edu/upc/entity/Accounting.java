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
@Table(name = "accounting")
public class Accounting {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long AccountingOfficerID;

	@NotEmpty(message = "Ingrese el nombre del encargado")
	@Column(name = "Namex", nullable = false, length = 50)
	private String Namex;
	
	@Size(min = 8, max = 8)
	@NotEmpty(message = "Ingrese el DNI del encargado")
	@Column(name = "DNI", nullable = false, unique = true)
	private String DNI;

	public Long getAccountingOfficerID() {
		return AccountingOfficerID;
	}

	public void setAccountingOfficerID(Long accountingOfficerID) {
		AccountingOfficerID = accountingOfficerID;
	}

	public String getNamex() {
		return Namex;
	}

	public void setNamex(String namex) {
		Namex = namex;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public Accounting() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Accounting(Long accountingOfficerID,
			@NotEmpty(message = "Ingrese el nombre del encargado") String namex,
			@NotEmpty(message = "Ingrese el DNI del encargado") String dNI) {

		super();
		AccountingOfficerID = accountingOfficerID;
		Namex = namex;
		DNI = dNI;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((AccountingOfficerID == null) ? 0 : AccountingOfficerID.hashCode());
		result = prime * result + ((DNI == null) ? 0 : DNI.hashCode());
		result = prime * result + ((Namex == null) ? 0 : Namex.hashCode());
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
		Accounting other = (Accounting) obj;
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
		if (Namex == null) {
			if (other.Namex != null)
				return false;
		} else if (!Namex.equals(other.Namex))
			return false;
		return true;
	}

}
