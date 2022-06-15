package team.domain;

import java.util.Date;
import lombok.Data;
import team.domain.*;
import team.infra.AbstractEvent;

@Data
public class PaymentCanceled extends AbstractEvent {

    private Long id;
    private Long orderId;
    private String payStatus;
    private Double payAmount;

    public PaymentCanceled(Payment aggregate) {
        super(aggregate);
    }

    public PaymentCanceled() {
        super();
    }
    // keep

}
