package board.orderservice.services;

import board.orderservice.models.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service")
public interface CustomerRestClientService {

    @GetMapping("/customers/{id}?projection=fullcustomer")
    public Customer CustomerById(@PathVariable Long id);
    @GetMapping("/customers?projection=fullcustomer")
    public PagedModel<Customer> AllCustomers();
}
