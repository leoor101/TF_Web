package pe.edu.upc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "Purchase_Folder")
public class Purchase_Folder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long PurchaseFolderID;

	@ManyToOne
	@JoinColumn(name = "SupplierID", nullable = false)
	private Supplier PFSupplier;

	@NotEmpty(message = "Ingrese el nombre de la carpeta de nombre")
	@Column(name = "FolderName", nullable = false, length = 50)
	private String FolderName;

	public Long getPurchaseFolderID() {
		return PurchaseFolderID;
	}

	public void setPurchaseFolderID(Long purchaseFolderID) {
		PurchaseFolderID = purchaseFolderID;
	}

	public Supplier getPFSupplier() {
		return PFSupplier;
	}

	public void setPFSupplier(Supplier pFSupplier) {
		PFSupplier = pFSupplier;
	}

	public String getFolderName() {
		return FolderName;
	}

	public void setFolderName(String folderName) {
		FolderName = folderName;
	}

	public Purchase_Folder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Purchase_Folder(Long purchaseFolderID, Supplier pFSupplier,
			@NotEmpty(message = "Ingrese el nombre de la carpeta de nombre") String folderName) {
		super();
		PurchaseFolderID = purchaseFolderID;
		PFSupplier = pFSupplier;
		FolderName = folderName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((FolderName == null) ? 0 : FolderName.hashCode());
		result = prime * result + ((PFSupplier == null) ? 0 : PFSupplier.hashCode());
		result = prime * result + ((PurchaseFolderID == null) ? 0 : PurchaseFolderID.hashCode());
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
		Purchase_Folder other = (Purchase_Folder) obj;
		if (FolderName == null) {
			if (other.FolderName != null)
				return false;
		} else if (!FolderName.equals(other.FolderName))
			return false;
		if (PFSupplier == null) {
			if (other.PFSupplier != null)
				return false;
		} else if (!PFSupplier.equals(other.PFSupplier))
			return false;
		if (PurchaseFolderID == null) {
			if (other.PurchaseFolderID != null)
				return false;
		} else if (!PurchaseFolderID.equals(other.PurchaseFolderID))
			return false;
		return true;
	}

}
