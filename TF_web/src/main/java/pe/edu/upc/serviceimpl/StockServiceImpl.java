package pe.edu.upc.serviceimpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.Stock;
import pe.edu.upc.repository.IStockRepository;
import pe.edu.upc.service.IStockService;

@Service
public class StockServiceImpl implements IStockService {

	@Autowired
	private IStockRepository stoC;

	@Transactional
	@Override
	public Stock insert(Stock stock) {
		// TODO Auto-generated method stub
		return stoC.save(stock);
	}

	@Transactional
	@Override
	public void delete(long idStock) {
		stoC.deleteById(idStock);

	}

	@Transactional(readOnly = true)
	@Override
	public List<Stock> list() {
		// TODO Auto-generated method stub
		return stoC.findAll();
	}

	@Override
	public Optional<Stock> findById(long id) {
		// TODO Auto-generated method stub
		return stoC.findById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Stock> fetchByStockstockIDWithProduct_Requirement(Long id) throws Exception {
		// TODO Auto-generated method stub
		return stoC.fetchByStockstockIDWithProduct_Requirement(id);
	}

	@Override
	public List<Stock> findDate(Date fecha) {
		// TODO Auto-generated method stub
		return stoC.findByCreateAt(fecha);
	}

	@Override
	public List<String[]> listStockedProducts() {
		// TODO Auto-generated method stub
		return stoC.stockedProducts();
	}

	@Override
	public List<String[]> listStockedProductss() {
		// TODO Auto-generated method stub
		return stoC.stockedProductss();
	}

	@Override
	public List<String[]> listStockedProductsss() {
		// TODO Auto-generated method stub
		return stoC.stockedProductsss();
	}

}
