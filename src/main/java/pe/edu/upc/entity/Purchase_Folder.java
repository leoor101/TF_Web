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
	private Long purchaseFolderID;

	@ManyToOne
	@JoinColumn(name = "supplierID", nullable = false)
	private Supplier supplier;

	@NotEmpty(message = "Ingrese el nombre de la carpeta de nombre")
	@Column(name = "folderName", nullable = false, length = 50)
	private String folderName;

	public Long getPurchaseFolderID() {
		return purchaseFolderID;
	}

	public void setPurchaseFolderID(Long purchaseFolderID) {
		this.purchaseFolderID = purchaseFolderID;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}


}
