package team.domain;

import java.util.Date;
import lombok.Data;
import team.domain.*;
import team.infra.AbstractEvent;

@Data
public class DeliveryCanceled extends AbstractEvent {

    private Long id;
    private Long orderId;
    private String orderStatus;
    private String customerAddr;
    private String customerTel;
    private String customerId;
    private Long deliveryId;

    public DeliveryCanceled(Delivery aggregate) {
        super(aggregate);
    }

    public DeliveryCanceled() {
        super();
    }
    // keep

}
