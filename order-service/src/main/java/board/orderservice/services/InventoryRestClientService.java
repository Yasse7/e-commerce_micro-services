package board.orderservice.services;

import board.orderservice.models.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "customer-service")
public interface customerRestClientService {

    @GetMapping("/customers/{id}?projection=fullcustomer")
    public Customer CustomerById(@PathVariable Long id);
    @GetMapping("/customers?projection=fullcustomer")
    public List<Customer> AllCustomer();
}
