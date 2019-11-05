package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Product_Requirement;
import pe.edu.upc.repository.IProduct_RequirementRepository;
import pe.edu.upc.service.IProduct_RequirementService;

@Service
public class Product_RequirementServiceImpl implements IProduct_RequirementService {

	@Autowired
	private IProduct_RequirementRepository productR;

	@Override
	@Transactional
	public Integer insert(Product_Requirement product) {
		int rpta = productR.searchNameProduct_Requirement(product.getName());
		if (rpta == 0) {
			productR.save(product);
		}
		return rpta;
	}

	@Override
	@Transactional
	public void delete(long id) {
		productR.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Product_Requirement> list() {
		return productR.findAll(Sort.by(Sort.Direction.DESC, "Name"));

	}

	@Override
	public Optional<Product_Requirement> listProduct_RequirementId(long id) {
		// TODO Auto-generated method stub
		return productR.findById(id);
	}

	@Override
	public List<Product_Requirement> findByName(String Name) {
		// TODO Auto-generated method stub
		return productR.findByName(Name);
	}

	

}
