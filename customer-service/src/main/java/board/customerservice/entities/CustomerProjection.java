package board.customerservice.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "fullcustomer" , types = Customer.class)
public interface CustomerProjection {
    public Long getId();
    public String getName();
    public  String getemail();
}
