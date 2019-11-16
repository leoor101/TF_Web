package pe.edu.upc.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Consulttypeorder")
public class Consulttypeorder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long consulttypeorderID;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "consult_id", nullable = false)
	private Users consultId;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "name_id")
	private List<Type_Order> consultName;

	public Long getConsulttypeorderID() {
		return consulttypeorderID;
	}

	public void setConsulttypeorderID(Long consulttypeorderID) {
		this.consulttypeorderID = consulttypeorderID;
	}

	public Users getConsultId() {
		return consultId;
	}

	public void setConsultId(Users consultId) {
		this.consultId = consultId;
	}

	public List<Type_Order> getConsultName() {
		return consultName;
	}

	public void setConsultName(List<Type_Order> consultName) {
		this.consultName = consultName;
	}
	

}
