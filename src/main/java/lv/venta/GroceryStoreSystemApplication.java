package lv.venta;

import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import lv.venta.repo.IProductRepo;

@SpringBootApplication
public class GroceryStoreSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(GroceryStoreSystemApplication.class, args);
	}
	@Bean
	public CommandLineRunner testDatabaseLayer(IProductRepo productRepo) {
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				ArrayList<Product> allProducts = new ArrayList<>(Arrays.asList(new Product("Apple", "Red", 0.85f, 10),new Product("Banana", "Yellow", 1.13f, 6),new Product("Peach", "Orange", 2.34f, 3)));
				productRepo.saveAll(allProducts);
			}
		};
	}
}
