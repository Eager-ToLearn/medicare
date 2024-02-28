package pac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import pac.global.GlobalData;
import pac.model.Product;
import pac.service.CategoryService;
import pac.service.ProductService;

@Controller
public class HomeController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductService productService;
	
	@GetMapping({"/","/home"})
	public String home(Model model)
	{
		model.addAttribute("products", productService.getAllProduct());
		//model.addAttribute("product", productService.getProById(id));
		return "index";
	}
	
	
	@GetMapping("/shop")
	public String shop(Model model)
	{
		model.addAttribute("cartCount", GlobalData.cart.size());
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("products", productService.getAllProduct());
		return "shop";
	}
	
	@GetMapping("/shop/category/{id}")
	public String shopByCategory(Model model, @PathVariable int id)
	{
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("cartCount", GlobalData.cart.size());
		model.addAttribute("products", productService.getAllProByCatId(id));    //getAllProductsByCategoryId(id));
		return "shop";
	}
	
	@GetMapping("/shop/viewproduct/{id}")
	public String viewProduct(Model model, @PathVariable int id)
	{
		model.addAttribute("cartCount", GlobalData.cart.size());
		model.addAttribute("product", productService.getProById(id));  //getProById(id));
		return "viewProduct";
	}
	
	@GetMapping("/cart/removeItem/{index}")
	public String cartItemRemove(@PathVariable int index)
	{
		GlobalData.cart.remove(index);
		return "redirect:/cart";
	}
	
	
	@GetMapping("/checkout")
	public String checkout(Model model)
	{
		model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
		return "checkout";
	}
	
	@GetMapping("/payNow")
	public String paymentDone()
	{
		return "paymentSuccessfull";
	}

}
