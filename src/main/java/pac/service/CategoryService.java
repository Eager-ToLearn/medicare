package pac.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pac.model.Category;
import pac.repository.CategoryRepo;

@Service
public class CategoryService {
	
	@Autowired
	CategoryRepo categoryRepo;
	
	public List<Category> getAllCategory()
	{
		return categoryRepo.findAll();
	}
	
	public void addCategory(Category category)
	{
		categoryRepo.save(category);
		
	}
	public void delCat(int id)
	{
		categoryRepo.deleteById(id);
	}
	public Category getCatById(int id)
	{
	return categoryRepo.findById(id).get();
	}

}
