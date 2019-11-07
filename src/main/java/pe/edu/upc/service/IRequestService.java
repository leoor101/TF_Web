package pe.edu.upc.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Request;;

public interface IRequestService {

	public Request insert(Request request);

	public void delete(long idRequest);

	List<Request> list();

	public Optional<Request> findById(long id);

	Optional<Request> fetchByRequestIdWithUserWhithRequiDetailsWithProduct(Long id) throws Exception;

	List<Request> findDate(Date fecha);

}
