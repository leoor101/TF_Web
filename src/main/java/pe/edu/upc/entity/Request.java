package pe.edu.upc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Request")
public class Request {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long RequestID;

	
	@ManyToOne
	@JoinColumn(name = "SupervisorID", nullable = false)
	private Supervisor RSupervisor;

	@ManyToOne
	@JoinColumn(name = "OrderTypeID", nullable = false)
	private Order_Type ROrderType;

	@ManyToOne
	@JoinColumn(name = "AccountingOfficerID", nullable = false)
	private Accounting_Officer RAccountingOfficer;

	@ManyToOne
	@JoinColumn(name = "PurchaseFolderID", nullable = false)
	private Purchase_Folder RPurchaseFolder;

	@NotNull(message = "Ingresar presupuesto de la solicitud")
	@Column(name = "Budget", columnDefinition = "Decimal(5,2)", nullable = false)
	private Double Budget;

	@NotNull(message = "La fecha es obligatoria")
	@Column(name = "CreationDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.TIMESTAMP)
	private Date CreationDate;

	public Long getRequestID() {
		return RequestID;
	}

	public void setRequestID(Long requestID) {
		RequestID = requestID;
	}

	

	public Supervisor getRSupervisor() {
		return RSupervisor;
	}

	public void setRSupervisor(Supervisor rSupervisor) {
		RSupervisor = rSupervisor;
	}

	public Order_Type getROrderType() {
		return ROrderType;
	}

	public void setROrderType(Order_Type rOrderType) {
		ROrderType = rOrderType;
	}

	public Accounting_Officer getRAccountingOfficer() {
		return RAccountingOfficer;
	}

	public void setRAccountingOfficer(Accounting_Officer rAccountingOfficer) {
		RAccountingOfficer = rAccountingOfficer;
	}

	public Purchase_Folder getRPurchaseFolder() {
		return RPurchaseFolder;
	}

	public void setRPurchaseFolder(Purchase_Folder rPurchaseFolder) {
		RPurchaseFolder = rPurchaseFolder;
	}

	public Double getBudget() {
		return Budget;
	}

	public void setBudget(Double budget) {
		Budget = budget;
	}

	public Date getCreationDate() {
		return CreationDate;
	}

	public void setCreationDate(Date creationDate) {
		CreationDate = creationDate;
	}

	public Request() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Request(Long requestID, Supervisor rSupervisor, Order_Type rOrderType,
			Accounting_Officer rAccountingOfficer, Purchase_Folder rPurchaseFolder,
			@NotNull(message = "Ingresar presupuesto de la solicitud") Double budget,
			@NotNull(message = "La fecha es obligatoria") Date creationDate) {
		super();
		RequestID = requestID;
		
		RSupervisor = rSupervisor;
		ROrderType = rOrderType;
		RAccountingOfficer = rAccountingOfficer;
		RPurchaseFolder = rPurchaseFolder;
		Budget = budget;
		CreationDate = creationDate;
	}



}
