package pe.edu.upc.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Supervisor")
public class Supervisor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long supervisorID;

<<<<<<< HEAD
	@NotEmpty(message = "Ingrese el nombre")
=======
	@Size(min=1,max=50)
	@NotEmpty(message = "Ingrese el nombre del supervisor")
>>>>>>> a8eb04192995fc46d48a58030f72747d7a8533a7
	@Column(name = "name", nullable = false, length = 50)
	private String name;

	
	@NotNull(message = "Ingrese los años de experiencia del supervisor")
	@Column(name = "yearsExperience", nullable = false)
	private int yearsExperience;

	@Size(min=7,max=9)
	@NotEmpty(message = "Ingrese el teléfono del supervisor")
	@Column(name = "phoneNumber", nullable = false, length = 9)
	private String phoneNumber;

	@Size(min=1,max=50)
	@NotEmpty(message = "Ingrese la dirección del supervisor")
	@Column(name = "address", nullable = false, length = 50)
	private String address;
	@OneToMany(mappedBy = "supervisor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Request> requests;
	
	
	public Supervisor()
	{
		requests=new ArrayList<>();
	}
	
	public List<Request> getRequests() {
		return requests;
	}

	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}

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
