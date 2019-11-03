package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.Supplier;
import pe.edu.upc.repository.ISupplierRepository;
import pe.edu.upc.service.ISupplierService;

@Service
public class SupplierServiceImpl implements ISupplierService {

	@Autowired
	private ISupplierRepository suR;

	@Override
	@Transactional
	public Integer insert(Supplier supplier) {
		int rpta=suR.findSupplierName(supplier.getName());
		if(rpta==0) {
			suR.save(supplier);
		}
		return rpta;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Supplier> list() {
		return suR.findAll(Sort.by(Sort.Direction.DESC, "Name"));
	}

	@Override
	public Optional<Supplier> listarId(long supplierID) {
		return suR.findById(supplierID);
	}

	@Override
	public List<Supplier> findByName(String Name) {
		return suR.findByName(Name);
	}



	@Override
	@Transactional
	public void delete(long supplierID) {
		suR.deleteById(supplierID);
	}
}
