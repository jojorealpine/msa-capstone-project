package team.domain;

import java.util.Date;
import lombok.Data;
import team.domain.*;
import team.infra.AbstractEvent;

@Data
public class OrderCanceled extends AbstractEvent {

    private Long id;
    private Long orderId;
    private Long customerId;
    private String productName;
    private Float productPrice;
    private Float orderTotalPrice;
    private String orderStatus;
    private Date orderDate;
    private String customerAddr;
    private String customerTel;

    public OrderCanceled(Order aggregate) {
        super(aggregate);
    }

    public OrderCanceled() {
        super();
    }
    // keep

}
