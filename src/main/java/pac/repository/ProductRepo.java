package pac.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pac.model.Product;

public interface ProductRepo extends JpaRepository<Product, Integer>{

	List<Product> findAllByCategoryId(int id);

}
