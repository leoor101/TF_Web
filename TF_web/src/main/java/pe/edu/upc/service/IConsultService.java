package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Consult;

public interface IConsultService {

	List<Consult> list();

	public Optional<Consult> findById(long id);

	Optional<Consult> fetchByConsultIdWithUserWhithRequiDetailsWithProduct(Long id) throws Exception;

	List<String[]> listConsultType();

}
