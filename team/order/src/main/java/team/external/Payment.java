package team.external;

import java.util.Date;
import lombok.Data;

@Data
public class Payment {

    private Long id;
    private Long orderId;
    private String payStatus;
    private Double payAmount;
    // keep

}
