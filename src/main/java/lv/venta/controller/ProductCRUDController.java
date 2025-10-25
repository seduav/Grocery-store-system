package lv.venta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.validation.Valid;
import lv.venta.Product;
import lv.venta.service.ICRUDProductService;

@Controller
@RequestMapping("/product")
public class ProductCRUDController {
	
	@Autowired
	private ICRUDProductService crudService;
	
	@GetMapping("/all") //localhost:8080/product/all
	public String getProductAll(Model model) {
		try {
			model.addAttribute("myobjs", crudService.retrieveAll());
			return "product-page";
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "error-page";
		}
	}
	
	@GetMapping("/one")
	public String getProductoneId(@RequestParam int id, Model model) {
		try {
			model.addAttribute("myobj", crudService.retrieveById(id));
			return "show-product-page";
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "error-page";
		}

	}
	
	@GetMapping("/all/{id}")
	public String getProductAllId(@PathVariable int id, Model model) {
		try {
			model.addAttribute("myobj", crudService.retrieveById(id));
			model.addAttribute("mytitle", "All products");
			return "product-page-3";
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "error-page";
		}

	}
	
	@GetMapping("/insert")
	public String getProductInsert(Model model) {
		model.addAttribute("product", new Product());
		return "insert-product-page";
	}

	@PostMapping("/insert")
	public String postProductInsert(@Valid Product product, BindingResult result) {
		if (result.hasErrors()) {
			return "insert-product-page";
		} else {

			try {
				crudService.create(product);
				return "redirect:/product/all";
			} catch (Exception e) {
				return "redirect:/error";
			}
		}
	}
	
	@GetMapping("/update")
	public String getProductUpateById(@RequestParam int id, Model model ) {
		try {
			Product product = crudService.retrieveById(id);
			model.addAttribute("product", product);
			model.addAttribute("id", id);
			return "update-product-page";
				
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "error-page";
		}
	}
	
	@PostMapping("/update")
	public String postProductInsert(@RequestParam int id,  @Valid Product product, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("id", id);
			return "update-product-page";
		} else {

			try {
				crudService.updateById(id, product);
				return "redirect:/product/all/" + id;
			} catch (Exception e) {
				return "redirect:/error";
			}
		}
	}
	
	@GetMapping("/delete")
	public String getProductDeleteById(@RequestParam int id, Model model) {
		try {
			crudService.deleteById(id);
			model.addAttribute("myobjs", crudService.retrieveAll());
			return "product-page";
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "error-page";
		}
	}

}