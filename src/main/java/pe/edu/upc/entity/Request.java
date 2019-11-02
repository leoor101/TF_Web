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
	@JoinColumn(name = "UserID", nullable = false)
	private User RUser;

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

	public User getRUser() {
		return RUser;
	}

	public void setRUser(User rUser) {
		RUser = rUser;
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

	public Request(Long requestID, User rUser, Supervisor rSupervisor, Order_Type rOrderType,
			Accounting_Officer rAccountingOfficer, Purchase_Folder rPurchaseFolder,
			@NotNull(message = "Ingresar presupuesto de la solicitud") Double budget,
			@NotNull(message = "La fecha es obligatoria") Date creationDate) {
		super();
		RequestID = requestID;
		RUser = rUser;
		RSupervisor = rSupervisor;
		ROrderType = rOrderType;
		RAccountingOfficer = rAccountingOfficer;
		RPurchaseFolder = rPurchaseFolder;
		Budget = budget;
		CreationDate = creationDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Budget == null) ? 0 : Budget.hashCode());
		result = prime * result + ((CreationDate == null) ? 0 : CreationDate.hashCode());
		result = prime * result + ((RAccountingOfficer == null) ? 0 : RAccountingOfficer.hashCode());
		result = prime * result + ((ROrderType == null) ? 0 : ROrderType.hashCode());
		result = prime * result + ((RPurchaseFolder == null) ? 0 : RPurchaseFolder.hashCode());
		result = prime * result + ((RSupervisor == null) ? 0 : RSupervisor.hashCode());
		result = prime * result + ((RUser == null) ? 0 : RUser.hashCode());
		result = prime * result + ((RequestID == null) ? 0 : RequestID.hashCode());
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
		Request other = (Request) obj;
		if (Budget == null) {
			if (other.Budget != null)
				return false;
		} else if (!Budget.equals(other.Budget))
			return false;
		if (CreationDate == null) {
			if (other.CreationDate != null)
				return false;
		} else if (!CreationDate.equals(other.CreationDate))
			return false;
		if (RAccountingOfficer == null) {
			if (other.RAccountingOfficer != null)
				return false;
		} else if (!RAccountingOfficer.equals(other.RAccountingOfficer))
			return false;
		if (ROrderType == null) {
			if (other.ROrderType != null)
				return false;
		} else if (!ROrderType.equals(other.ROrderType))
			return false;
		if (RPurchaseFolder == null) {
			if (other.RPurchaseFolder != null)
				return false;
		} else if (!RPurchaseFolder.equals(other.RPurchaseFolder))
			return false;
		if (RSupervisor == null) {
			if (other.RSupervisor != null)
				return false;
		} else if (!RSupervisor.equals(other.RSupervisor))
			return false;
		if (RUser == null) {
			if (other.RUser != null)
				return false;
		} else if (!RUser.equals(other.RUser))
			return false;
		if (RequestID == null) {
			if (other.RequestID != null)
				return false;
		} else if (!RequestID.equals(other.RequestID))
			return false;
		return true;
	}

}
