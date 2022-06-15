package team.domain;

import java.util.Date;
import lombok.Data;
import team.domain.*;
import team.infra.AbstractEvent;

@Data
public class DeliveryStarted extends AbstractEvent {

    private Long id;
    private Long orderId;
    private String orderStatus;
    private String customerAddr;
    private String customerTel;
    private String customerId;
    private Long deliveryId;
    // keep

}
