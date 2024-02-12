package pac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import pac.global.GlobalData;
import pac.model.Product;
import pac.service.ProductService;

public class CartController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/addToCart/{id}")
	public String addToCart( @PathVariable int id)
	{
		GlobalData.cart.add(productService.getProById(id));
		return "redirect:/shop";
	}
	
	@GetMapping("/cart")
	public String cart(Model model)
	{
		model.addAttribute("cartCount", GlobalData.cart.size());
		model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
		model.addAttribute("cart", GlobalData.cart);
		return "cart";
	}

}
