package lv.venta.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.stereotype.Service;
import lv.venta.Product;
import lv.venta.service.ICRUDProductService;
import lv.venta.service.IFilterProductService;

@Service
public class ProductServiceImpl implements ICRUDProductService, IFilterProductService{

	ArrayList<Product> allProducts = new ArrayList<>(Arrays.asList(new Product("Apple", "Red", 0.85f, 10),new Product("Banana", "Yellow", 1.13f, 6),new Product("Peach", "Orange", 2.34f, 3)));

	@Override
	public Product create(Product product) throws Exception {
		System.out.println("-----------------");
		System.out.println(product);
		if(product == null)throw new Exception("Product null");
		for(Product tempP: allProducts) {
			if(tempP.getTitle().equals(product.getTitle()) && tempP.getDescription().equals(product.getDescription())) {
				tempP.setQuantity(tempP.getQuantity()+1);
				return tempP;
			}
		}
		Product newProduct = new Product(product.getTitle(), product.getDescription(), product.getPrice(), product.getQuantity());
		System.out.println("!!!!" + newProduct);
		allProducts.add(newProduct);
		return newProduct;
	}

	@Override
	public ArrayList<Product> retrieveAll() throws Exception {
		if(allProducts.isEmpty()) throw new Exception("Product list empty");
		return allProducts;
	}

	@Override
	public Product retrieveById(int id) throws Exception {
		if(id < 0) throw new Exception("Invalid given ID");
		for(Product tempP: allProducts) {
			if(tempP.getId() == id)
				return tempP;
		}
		throw new Exception("Product not found by id: " + id);
	}

	@Override
	public void updateById(int id, Product product) throws Exception {
		if(product == null)throw new Exception("Product null");
		Product updateProduct = retrieveById(id);
		updateProduct.setPrice(product.getPrice());
		updateProduct.setTitle(product.getTitle());
		updateProduct.setDescription(product.getDescription());
		updateProduct.setQuantity(product.getQuantity());
		
	}

	@Override
	public Product deleteById(int id) throws Exception {
		Product deleteProduct = retrieveById(id);
		allProducts.remove(deleteProduct);
		return deleteProduct;
	}
	@Override
	public ArrayList<Product> filterProductByPriceTreshold(float priceTreshold) throws Exception {
		if(priceTreshold <= 0 || priceTreshold > 1000)throw new Exception("Wrong price");
		ArrayList<Product> filteredProducts = new ArrayList<>();
		for(Product tempP: allProducts) {
			if(tempP.getPrice() <= priceTreshold) {
				filteredProducts.add(tempP);
			}
		}
		return filteredProducts;
	}

	@Override
	public ArrayList<Product> filterProductByQuantityTreshold(int quantityTreshold) throws Exception {
		if(quantityTreshold <= 0 || quantityTreshold > 1000)throw new Exception("Wrong quantity");
		ArrayList<Product> filteredProducts = new ArrayList<>();
		for(Product tempP: allProducts) {
			if(tempP.getQuantity() <= quantityTreshold) {
				filteredProducts.add(tempP);
			}
		}
		return filteredProducts;
	}


	@Override
	public ArrayList<Product> filterByTitleOrDescription(String searchText) throws Exception {
		if(searchText == null) throw new Exception("Wrong search text");
		ArrayList<Product> filteredProducts = new ArrayList<>();
		for(Product tempP: allProducts)
		{
			if(tempP.getTitle().toLowerCase().contains(searchText.toLowerCase()) ||
					tempP.getDescription().toLowerCase().contains(searchText.toLowerCase())) {
				filteredProducts.add(tempP);
			}
		}
		return filteredProducts;	
	}

	@Override
	public float calculateProductsTotalValue() throws Exception {
		if(allProducts.isEmpty()) throw new Exception("There is no such product!");

		float result = 0;
		for(Product tempP : allProducts) {
			result+=tempP.getPrice() * tempP.getQuantity();
		}
		return result;
	}

}