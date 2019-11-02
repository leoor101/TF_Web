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

	public Accounting_Officer(Long accountingOfficerID, String name, String dNI) {
		super();
		AccountingOfficerID = accountingOfficerID;
		Name = name;
		DNI = dNI;
	}

}
