package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Purchase_Folder;;

public interface IPurchase_FolderService {
	
	public Integer insert(Purchase_Folder purchase);

	public void delete(long id);

	List<Purchase_Folder> list();

	Optional<Purchase_Folder> listPurchase_FolderId(long id);	
	List<Purchase_Folder> findByName(String folderName);
	
	public List<Purchase_Folder> fetchPurchase_FolderBySupplierName(String nameUniversidad);

	
}
