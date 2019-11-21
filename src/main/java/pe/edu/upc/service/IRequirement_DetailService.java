package pe.edu.upc.service;

import pe.edu.upc.entity.Requirement_Detail;

public interface IRequirement_DetailService {
	public Integer insert(  Requirement_Detail requestd);

	public void delete(Long idRequestDetail);
	
	
}
