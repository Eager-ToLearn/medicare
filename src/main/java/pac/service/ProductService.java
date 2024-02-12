package pac.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pac.model.Product;
import pac.repository.ProductRepo;

@Service
public class ProductService {
	
	@Autowired
	ProductRepo productRepo;
	
	public List<Product> getAllProduct()
	{
		
		return productRepo.findAll();
	}
	
	public void addProduct(Product product)
	{
		productRepo.save(product);
	}
	
	public void removeById(int id)
	{
		productRepo.deleteById(id);
	}
	
	public Product getProById(int id)
	{
		return productRepo.findById(id).get();
	}
	
	public List<Product> getAllProByCatId(int id)
	{
		return productRepo.findAllByCategoryId(id);
	}

}
