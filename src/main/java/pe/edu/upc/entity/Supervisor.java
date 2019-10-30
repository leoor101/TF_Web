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
	private int SupervisorID;

	@NotEmpty(message = "Ingrese el nombre del supervisor")
	@Column(name = "Name", nullable = false, length = 50)
	private String Name;

	@NotNull(message = "Ingrese los años de experiencia del supervisor")
	@Column(name = "YearsExperience", nullable = false)
	private int YearsExperience;

	@NotEmpty(message = "Ingrese el teléfono del supervisor")
	@Column(name = "PhoneNumber", nullable = false, length = 7)
	private String PhoneNumber;

	@NotEmpty(message = "Ingrese la dirección del supervisor")
	@Column(name = "Address", nullable = false, length = 50)
	private String Address;

	public int getSupervisorID() {
		return SupervisorID;
	}

	public void setSupervisorID(int supervisorID) {
		SupervisorID = supervisorID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getYearsExperience() {
		return YearsExperience;
	}

	public void setYearsExperience(int yearsExperience) {
		YearsExperience = yearsExperience;
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

	public Supervisor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Supervisor(int supervisorID, @NotEmpty(message = "Ingrese el nombre del supervisor") String name,
			@NotEmpty(message = "Ingrese los años de experiencia del supervisor") int yearsExperience,
			@NotEmpty(message = "Ingrese el teléfono del supervisor") String phoneNumber,
			@NotEmpty(message = "Ingrese la dirección del supervisor") String address) {
		super();
		SupervisorID = supervisorID;
		Name = name;
		YearsExperience = yearsExperience;
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
		result = prime * result + SupervisorID;
		result = prime * result + YearsExperience;
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
		Supervisor other = (Supervisor) obj;
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
		if (SupervisorID != other.SupervisorID)
			return false;
		if (YearsExperience != other.YearsExperience)
			return false;
		return true;
	}

}
