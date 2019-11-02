package pe.edu.upc.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Requirement_Detail")
public class Requirement_Detail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long RequirementDetailID;

	@ManyToOne
	@JoinColumn(name = "ProductRequirementID", nullable = false)
	private Product_Requirement RDProductRequirement;

	@ManyToOne
	@JoinColumn(name = "RequestID", nullable = false)
	private Request RDRequest;

	public Long getRequirementDetailID() {
		return RequirementDetailID;
	}

	public void setRequirementDetailID(Long requirementDetailID) {
		RequirementDetailID = requirementDetailID;
	}

	public Product_Requirement getRDProductRequirement() {
		return RDProductRequirement;
	}

	public void setRDProductRequirement(Product_Requirement rDProductRequirement) {
		RDProductRequirement = rDProductRequirement;
	}

	public Request getRDRequest() {
		return RDRequest;
	}

	public void setRDRequest(Request rDRequest) {
		RDRequest = rDRequest;
	}

	public Requirement_Detail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Requirement_Detail(Long requirementDetailID, Product_Requirement rDProductRequirement, Request rDRequest) {
		super();
		RequirementDetailID = requirementDetailID;
		RDProductRequirement = rDProductRequirement;
		RDRequest = rDRequest;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((RDProductRequirement == null) ? 0 : RDProductRequirement.hashCode());
		result = prime * result + ((RDRequest == null) ? 0 : RDRequest.hashCode());
		result = prime * result + ((RequirementDetailID == null) ? 0 : RequirementDetailID.hashCode());
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
		Requirement_Detail other = (Requirement_Detail) obj;
		if (RDProductRequirement == null) {
			if (other.RDProductRequirement != null)
				return false;
		} else if (!RDProductRequirement.equals(other.RDProductRequirement))
			return false;
		if (RDRequest == null) {
			if (other.RDRequest != null)
				return false;
		} else if (!RDRequest.equals(other.RDRequest))
			return false;
		if (RequirementDetailID == null) {
			if (other.RequirementDetailID != null)
				return false;
		} else if (!RequirementDetailID.equals(other.RequirementDetailID))
			return false;
		return true;
	}

}
