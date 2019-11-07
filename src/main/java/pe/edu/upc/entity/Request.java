package pe.edu.upc.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Request")
public class Request {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long requestID;

	@Column(name = "CreationDate")
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_OrderID", nullable = false)
	private Type_Order type_OrderID;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "accountingID", nullable = false)
	private Accounting accountingID;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "purchaseID", nullable = false)
	private Purchase_Folder purchaseID;

	
	public Long getRequestID() {
		return requestID;
	}

	public void setRequestID(Long requestID) {
		this.requestID = requestID;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Users getUserId() {
		return userId;
	}

	public void setUserId(Users userId) {
		this.userId = userId;
	}

	public List<Requirement_Detail> getRequiDetails() {
		return requiDetails;
	}

	public void setRequiDetails(List<Requirement_Detail> requiDetails) {
		this.requiDetails = requiDetails;
	}

	public Supervisor getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Supervisor supervisor) {
		this.supervisor = supervisor;
	}

	public Type_Order getType_OrderID() {
		return type_OrderID;
	}

	public void setType_OrderID(Type_Order type_OrderID) {
		this.type_OrderID = type_OrderID;
	}

	public Accounting getAccountingID() {
		return accountingID;
	}

	public void setAccountingID(Accounting accountingID) {
		this.accountingID = accountingID;
	}

	public Purchase_Folder getPurchaseID() {
		return purchaseID;
	}

	public void setPurchaseID(Purchase_Folder purchaseID) {
		this.purchaseID = purchaseID;
	}
	
	public Request()
	{
		this.requiDetails=new ArrayList<>();
	}
	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}
	public void setrequiDetails(List<Requirement_Detail> requiDetails) {
		this.requiDetails = requiDetails;
	}

	public void addrequiDetails(Requirement_Detail item) {
		this.requiDetails.add(item);
	}
	
	public double getTotal()
	{
		return requiDetails.stream().collect(Collectors.summingDouble(Requirement_Detail::calculateAmount));
		
	}

}
