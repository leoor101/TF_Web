package pe.edu.upc.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Consulttypeorder")
public class Consulttypeorder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long consulttypeorderID;

	@Column(name = "Consulttypeorder")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private Users userId;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "details_id")
	private List<Requirement_Detail> requiDetails;
			/////-------------------------------------------
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "supervisorID", nullable = false)
	private Supervisor supervisor;


}
