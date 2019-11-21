package pe.edu.upc.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Requirement_Detail;
import pe.edu.upc.repository.IRequirement_DetailRepository;
import pe.edu.upc.service.IRequirement_DetailService;

@Service
public class Requirement_DetailServiceImpl implements IRequirement_DetailService{

	@Autowired
	private IRequirement_DetailRepository reDetailR;
	


	@Override
	public void delete(Long id) {
		reDetailR.deleteById(id);
	}


	


	@Override
	public Integer insert(Requirement_Detail requestd) {
		// TODO Auto-generated method stub
		Requirement_Detail qwe=reDetailR.save(requestd);
		if (qwe == null) {
			return 0;
		} else {
			return 1;
		}
	
	}

	
}
