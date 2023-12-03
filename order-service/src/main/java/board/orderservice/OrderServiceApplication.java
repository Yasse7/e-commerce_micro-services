package board.orderservice;

import board.orderservice.entities.Order;
import board.orderservice.entities.ProductItem;
import board.orderservice.enums.Orderstatus;
import board.orderservice.models.Customer;
import board.orderservice.models.Product;
import board.orderservice.repositories.OrderRepository;
import board.orderservice.repositories.ProductItemRepository;
import board.orderservice.services.InventoryRestClientService;
import board.orderservice.services.CustomerRestClientService;
import com.ecwid.consul.v1.acl.model.NewAcl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}
@Bean
	CommandLineRunner start(OrderRepository orderRepository ,
							ProductItemRepository productItemRepository ,
							CustomerRestClientService customerRestClientService,
							InventoryRestClientService inventoryRestClientService){
		return args -> {
			List<Customer> customers = customerRestClientService.AllCustomers().getContent().stream().toList();
			List<Product> products = inventoryRestClientService.AllProducts().getContent().stream().toList();
			Random random =new  Random();
			for (int i = 0; i < 20; i++) {
				Order order = Order.builder()
						.customerId(customers.get(random.nextInt(customers.size())).getId())
						.status(Math.random()>0.5?Orderstatus.PENDING:Orderstatus.CREATED)
						.createdAt(new Date())
						.build();
				Order savedOrder =	orderRepository.save(order);
				for (int j = 0; j < products.size() ; j++) {
					if(Math.random()>0.70)
					{
						ProductItem productItem = ProductItem.builder()
								.order(savedOrder)
								.productId(products.get(j).getId())
								.price(products.get(j).getPrice())
								.quantity(1+random.nextInt(10))
								.discount(Math.random())
								.build();
						productItemRepository.save(productItem);
					}
					
				}
			}
		};
}

}
