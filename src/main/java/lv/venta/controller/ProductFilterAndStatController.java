package lv.venta.controller;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import lv.venta.Product;
import lv.venta.service.IFilterProductService;

@Controller
@RequestMapping("/product/info")
public class ProductFilterAndStatController {

	@Autowired
	private IFilterProductService filterService;
	
	@GetMapping("/filter/price")
	public String getProductInfoFilterByPrice(@RequestParam float threshold,
			Model model) {
		try {
			ArrayList<Product> result = filterService.filterProductByPriceTreshold(threshold);
			model.addAttribute("myobjs", result);
			model.addAttribute("mytitle", "Products filtered by price");
			return "product-page";
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "error-page";
		}
	}
	
	@GetMapping("/filter/quantity")
	public String getProductInfoFilterByQuantity(@RequestParam int threshold, Model model) {
		try {
			ArrayList<Product> result = filterService.filterProductByQuantityTreshold(threshold);
			model.addAttribute("myobjs", result);
			model.addAttribute("mytitle", "Products filtered by quantity");
			return "product-page";
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "error-page";
		}
	}
	
	@GetMapping("/filter/text")
	public String getProductInfoFilterByTitleOrDescriptionText(@RequestParam String keyword,
			Model model){
		try {
			ArrayList<Product> result = filterService.filterByTitleOrDescription(keyword);
			model.addAttribute("myobjs", result);
			model.addAttribute("mytitle", "Products filtered by title and description");
			return "product-page";
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "error-page";
		}
	}
	
}