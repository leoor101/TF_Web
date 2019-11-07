package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Purchase_Folder;

@Repository
public interface IPurchase_FolderRepository extends JpaRepository<Purchase_Folder, Long> {

	@Query("select count(a.folderName) from Purchase_Folder a where a.folderName =:folderName")
	public int searchNamePurchase_Folder(@Param("folderName") String folderName);

	@Query("select a from Purchase_Folder a where a.folderName like %?1%")
	List<Purchase_Folder> fetchPurchase_FolderByfolderName(String folderName);

	@Query("select p from Purchase_Folder p where p.supplier.name like %?1%")
	public List<Purchase_Folder> findPurchase_FolderByNameSupplier(String name);

	
	
	
	
}
