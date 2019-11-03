package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Accounting_Officer;
import pe.edu.upc.repository.IAccounting_OfficerRepository;
import pe.edu.upc.service.IAccounting_OfficerService;

@Service
public class Accounting_OfficerServiceImpl implements IAccounting_OfficerService{

	@Autowired
	private IAccounting_OfficerRepository aR;
	
	
	@Override
	@Transactional
	public Integer insert(Accounting_Officer acco) {
		int rpta = aR.searchNameAccountingOfficer(acco.getName());
		if (rpta == 0) {
			aR.save(acco);
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
	public List<Accounting_Officer> list() {
		return aR.findAll(Sort.by(Sort.Direction.ASC, "Name"));
	}

	@Override
	public Optional<Accounting_Officer> listarAccountingOfficerId(long id) {
		return aR.findById(id);
	}

	@Override
	public List<Accounting_Officer> findByName(String Name) {
		return aR.findByName(Name);
	}

}
