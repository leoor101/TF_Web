package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.Type_Order;
import pe.edu.upc.repository.ITypeorderRepository;
import pe.edu.upc.service.ITypeorderService;

@Service
public class TypeorderServiceImpl implements ITypeorderService {

	@Autowired
	private ITypeorderRepository caR;

	@Override
	@Transactional
	public Integer insert(Type_Order type_order) {
		int rpta= caR.findNameTypeOrder(type_order.getName());
		if(rpta ==0) {
			caR.save(type_order);
		}
		return rpta;
	}

	@Override
	@Transactional
	public void delete(long idTypeorder) {
		caR.deleteById(idTypeorder);

	}

	@Override
	@Transactional
	public void modify(Type_Order type_order) {
		caR.save(type_order);

	}

	@Override
	@Transactional(readOnly=true)
	public List<Type_Order> list() {
		// TODO Auto-generated method stub
		return caR.findAll(Sort.by(Sort.Direction.ASC,"name"));
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Type_Order> listId(long idTypeorder) {
		// TODO Auto-generated method stub
		return caR.findById(idTypeorder);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Type_Order> findByName(String name) {
		// TODO Auto-generated method stub
		return caR.findByName(name);
	}

	

}
