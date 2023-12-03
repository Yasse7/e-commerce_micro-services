package board.inventoryservice;

import board.inventoryservice.Repositories.ProductRepository;
import board.inventoryservice.entities.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ProductRepository productRepository) {
		return args -> {
			productRepository.saveAll(List.of(
					Product.builder().name("pc").price(5555).quantity(777777).build(),
					Product.builder().name("phone").price(5555).quantity(777777).build(),
					Product.builder().name("disk").price(5555).quantity(777777).build()
			));
			productRepository.findAll().forEach(System.out::println);
		};
	}

}
