package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Product_Requirement;
import pe.edu.upc.entity.Purchase_Folder;
import pe.edu.upc.repository.IProduct_RequirementRepository;
import pe.edu.upc.repository.IPurchase_FolderRepository;
import pe.edu.upc.service.IProduct_RequirementService;
import pe.edu.upc.service.IPurchase_FolderService;

@Service
public class Purchase_FolderServiceImpl implements IPurchase_FolderService {

	@Autowired
	private IPurchase_FolderRepository purchaseR;
	
	@Override
	@Transactional
	public Integer insert(Purchase_Folder product) {
		int rpta = purchaseR.searchNamePurchase_Folder(product.getFolderName());
		if (rpta == 0) {
			purchaseR.save(product);
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
		return purchaseR.findByName(folderName);
	}

	@Override
	public List<Purchase_Folder> fetchPurchase_FolderBySupplierName(String nameUniversidad) {
		// TODO Auto-generated method stub
		return null;
	}

	

	

}
