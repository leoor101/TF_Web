package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.upc.entity.Supplier;

public interface ISupplierRepository extends JpaRepository<Supplier,Long> {

}
