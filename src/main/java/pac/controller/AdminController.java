package pac.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import pac.dto.ProductDTO;
import pac.model.Category;
import pac.model.Product;
import pac.service.CategoryService;
import pac.service.ProductService;

@Controller
public class AdminController {
	
	public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductService productService;
	
	
	@GetMapping("/admin")
	public String adminHome() {
		
		
		return "adminHome";
	}
	
	@GetMapping("/admin/categories")
	public String getCat(Model model)
	{
		model.addAttribute("categories", categoryService.getAllCategory());
		
		return "categories";
	}
	
	@GetMapping("/admin/categories/add")
	public String getCatAdd(Model model)
	{
		model.addAttribute("category", new Category());
		return "addCategories";
	}
	@PostMapping("/admin/categories/add")
	public String postCatAdd(@ModelAttribute("category") Category category)
	{
		
		categoryService.addCategory(category);
		return "redirect:/admin/categories";
	}
	
	@GetMapping("/admin/categories/delete/{id}")
	public String deleteCat(@PathVariable int id)
	{
		categoryService.delCat(id);
		return "redirect:/admin/categories";
	}
	
	@GetMapping("/admin/categories/update/{id}")
	public String updateCat(@PathVariable int id, Model model)
	{
		Category category = categoryService.getCatById(id);
		 model.addAttribute("category",category);
		 return "addCategories";
		
		
	}
	
	
	//Product 



	@GetMapping("/admin/products")
	public String products(Model model)
	{
		model.addAttribute("products", productService.getAllProduct());
		return "products";
	}
	
	@GetMapping("/admin/products/add")
	public String AddProduct(Model model)
	{
		model.addAttribute("productDTO", new ProductDTO());
		model.addAttribute("categories", categoryService.getAllCategory());
		
		return "AddProduct";
		
	}
	
	@PostMapping("/admin/products/add")
	public String AddProPost(@ModelAttribute("productDTO") ProductDTO productDTO,
			@RequestParam("productImage") MultipartFile file,
			@RequestParam("imgName") String imgName) throws IOException
	{
		Product product = new Product();
		product.setId(productDTO.getId());
		product.setName(productDTO.getName());
		product.setCategory(categoryService.getCatById(productDTO.getCategory_Id()));
		product.setPrice(productDTO.getPrice());
		product.setSize(productDTO.getSize());
		product.setAbout(productDTO.getAbout());
		
		
		String imageUUID;
		if(!file.isEmpty())
		{
			imageUUID= file.getOriginalFilename();
			Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
			Files.write(fileNameAndPath, file.getBytes());	
		}
		
		else
		{
			imageUUID = imgName;
			
		}
		
		product.setImageName(imageUUID);
		productService.addProduct(product);
		
		
		return "redirect:/admin/products";
	}
	
	@GetMapping("/admin/product/delete/{id}")
	public String deletePro(@PathVariable int id)
	{
		productService.removeById(id);
		return "redirect:/admin/products";
	}
	
	@GetMapping("/admin/product/update/{id}")
	public String updatePro(@PathVariable int id, Model model)
	{
		Product product = productService.getProById(id);
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(product.getId());
		productDTO.setName(product.getName());
		productDTO.setCategory_Id(product.getCategory().getId());
		productDTO.setPrice(product.getPrice());
		productDTO.setSize(product.getSize());
		productDTO.setAbout(product.getAbout());
		productDTO.setImageName(product.getImageName());
		
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("productDTO", productDTO);
		

		return "AddProduct";
	}

}