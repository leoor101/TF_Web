package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.Stock;
import pe.edu.upc.repository.IStockRepository;
import pe.edu.upc.service.IStockService;

@Service
public class StockServiceImpl implements IStockService {

	@Autowired
	private IStockRepository stK;

	@Override
	@Transactional
	public Integer insert(Stock stock) {
		int rpta = stK.findStockName(stock.getName());
		if (rpta == 0) {
			stK.save(stock);
		}
		return rpta;
	}

	@Override
	@Transactional
	public void delete(long stockID) {
		stK.deleteById(stockID);

	}

	@Override
	@Transactional(readOnly = true)
	public List<Stock> list() {
		// TODO Auto-generated method stub
		return stK.findAll(Sort.by(Sort.Direction.DESC, "name"));
	}

	@Override
	public Optional<Stock> listarId(long stockID) {
		// TODO Auto-generated method stub
		return stK.findById(stockID);
	}

	@Override
	public List<Stock> findByName(String name) {
		// TODO Auto-generated method stub
		return stK.findByName(name);
	}

	@Override
	public void insertmodified(Stock stock) {
		stK.save(stock);

	}

}
