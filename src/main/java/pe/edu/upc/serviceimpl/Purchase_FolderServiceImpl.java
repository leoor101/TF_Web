package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.Purchase_Folder;
import pe.edu.upc.repository.IPurchase_FolderRepository;
import pe.edu.upc.service.IPurchase_FolderService;

@Service
public class Purchase_FolderServiceImpl implements IPurchase_FolderService {

	@Autowired
	private IPurchase_FolderRepository purchaseR;
	
	@Override
	@Transactional
	public Integer insert(Purchase_Folder purchasef) {
		int rpta = purchaseR.searchNamePurchase_Folder(purchasef.getFolderName());
		if (rpta == 0) {
			purchaseR.save(purchasef);
		}
		return rpta;
	}

	@Override
	@Transactional
	public void delete(long id) {
		purchaseR.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Purchase_Folder> list() {
		return purchaseR.findAll(Sort.by(Sort.Direction.DESC, "folderName"));
	}

	@Override
	public Optional<Purchase_Folder> listPurchase_FolderId(long id) {
		// TODO Auto-generated method stub
		return purchaseR.findById(id);
	}

	@Override
	public List<Purchase_Folder> findByName(String folderName) {
		// TODO Auto-generated method stub
		return purchaseR.fetchPurchase_FolderByfolderName(folderName);
	}

	@Override
	public List<Purchase_Folder> fetchPurchase_FolderBySupplierName(String nameUniversidad) {
		// TODO Auto-generated method stub
		return purchaseR.findPurchase_FolderByNameSupplier(nameUniversidad);
	}

	

	

}
