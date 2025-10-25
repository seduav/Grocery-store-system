package lv.venta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import lv.venta.service.ICRUDProductService;

@Controller
public class FirstController {

	@Autowired
	private ICRUDProductService crudService;

	@GetMapping("/hello") // localhost:8080/hello
	public String getHello() {
		System.out.println("Hello");
		return "hello-page";
	}

	@GetMapping("/hello/msg")
	public String getHelloMsg(Model model) {
		model.addAttribute("mymsg", "Hello!");
		return "msg-page";
	}

	@GetMapping("/product/test")
	public String getProductTest(Model model) {
		try {
			model.addAttribute("myobj", crudService.retrieveAll().get(0));
			return "product-page";
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "error-page";
		}
	}

	@GetMapping("/error")
	public String getError() {
		return "error-page";
	}	

}