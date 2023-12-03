package board.orderservice.web;

import board.orderservice.entities.Order;
import board.orderservice.models.Customer;
import board.orderservice.models.Product;
import board.orderservice.repositories.OrderRepository;
import board.orderservice.repositories.ProductItemRepository;
import board.orderservice.services.CustomerRestClientService;
import board.orderservice.services.InventoryRestClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderRestController {
   private OrderRepository orderRepository;
   private ProductItemRepository productItemRepository ;

   private CustomerRestClientService customerRestClientService ;
   private InventoryRestClientService inventoryRestClientService;

    public OrderRestController(OrderRepository orderRepository, ProductItemRepository productItemRepository, CustomerRestClientService customerRestClientService, InventoryRestClientService inventoryRestClientService) {
        this.orderRepository = orderRepository;
        this.productItemRepository = productItemRepository;
        this.customerRestClientService = customerRestClientService;
        this.inventoryRestClientService = inventoryRestClientService;
    }

    @GetMapping("/fullOrder/{id}")
    public Order getOrder(@PathVariable Long id){
            Order order =orderRepository.findById(id).get();
            Customer customer = customerRestClientService.CustomerById(order.getCustomerId());
            order.setCustomer(customer);
            order.getProductItemList().forEach(pi ->
            {
                Product product = inventoryRestClientService.ProductById(pi.getProductId());
                pi.setProduct(product);

            });
            return order ;
    }
}
