package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Accounting;
import pe.edu.upc.repository.IAccounting_OfficerRepository;
import pe.edu.upc.service.IAccounting_OfficerService;

@Service
public class Accounting_OfficerServiceImpl implements IAccounting_OfficerService{

	@Autowired
	private IAccounting_OfficerRepository aR;
	
	
	@Override
	@Transactional
	public Integer insert(Accounting accounting) {
		int rpta = aR.searchDNIAccounting(accounting.getDNI());
		if (rpta == 0) {
			aR.save(accounting);
		}
		return rpta;
	}

	@Override
	@Transactional
	public void delete(long id) {
		aR.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Accounting> list() {
		return aR.findByListHello();
	}

	@Override
	public Optional<Accounting> listarAccountingOfficerId(long id) {
		return aR.findById(id);
	}

	@Override
	public List<Accounting> findByNamex(String Name) {
		return aR.findByNamex(Name);
	}

}
