package pe.edu.upc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Supervisor")
public class Supervisor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long supervisorID;

	@NotEmpty(message = "Ingrese el nombre del supervisor")
	@Column(name = "name", nullable = false, length = 50)
	private String name;

	@NotNull(message = "Ingrese los años de experiencia del supervisor")
	@Column(name = "yearsExperience", nullable = false)
	private int yearsExperience;

	@NotEmpty(message = "Ingrese el teléfono del supervisor")
	@Column(name = "phoneNumber", nullable = false, length = 7)
	private String phoneNumber;

	@NotEmpty(message = "Ingrese la dirección del supervisor")
	@Column(name = "address", nullable = false, length = 50)
	private String address;

	public Long getSupervisorID() {
		return supervisorID;
	}

	public void setSupervisorID(Long supervisorID) {
		this.supervisorID = supervisorID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYearsExperience() {
		return yearsExperience;
	}

	public void setYearsExperience(int yearsExperience) {
		this.yearsExperience = yearsExperience;
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
