package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Stock;

public interface IStockService {
	public Integer insert(Stock stock);

	public void delete(long stockID);

	List<Stock> list();

	Optional<Stock> listarId(long stockID);

	List<Stock> findByName(String name);

	public void insertmodified(Stock stock);
}
