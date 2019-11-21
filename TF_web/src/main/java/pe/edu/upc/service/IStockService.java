package pe.edu.upc.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Stock;

public interface IStockService {
	public Stock insert(Stock stock);

	public void delete(long idStock);

	List<Stock> list();

	public Optional<Stock> findById(long id);

	Optional<Stock> fetchByStockstockIDWithProduct_Requirement(Long id) throws Exception;

	List<Stock> findDate(Date fecha);

	List<String[]> listStockedProducts();

	List<String[]> listStockedProductss();

	List<String[]> listStockedProductsss();
}
