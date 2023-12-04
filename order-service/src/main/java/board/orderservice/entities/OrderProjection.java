package board.orderservice.entities;

import board.orderservice.enums.Orderstatus;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

@Projection(name = "fullOredr" ,types = Order.class)
public interface OrderProjection {
    Long getId();
    Date getCreatedAt();
    Long getCustomerId();
    Orderstatus getStatus();


}
