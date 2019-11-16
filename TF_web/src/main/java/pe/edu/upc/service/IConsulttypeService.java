package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;
import java.util.jar.Attributes.Name;

import pe.edu.upc.entity.Consulttypeorder;

public interface IConsulttypeService {

	List<Consulttypeorder> list();

	public Optional<Consulttypeorder> findById(long id);

	Optional<Consulttypeorder> fetchByIDWithUserWhithTypeOrderWithName(Long id) throws Exception;

	List<Consulttypeorder> findName(Name name);
}
