package team.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import team.OrderApplication;
import team.domain.OrderCanceled;
import team.domain.Ordered;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name = "Order_table")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @PostPersist
    public void onPostPersist() {
        Ordered ordered = new Ordered(this);
        BeanUtils.copyProperties(this, ordered);
        ordered.publishAfterCommit();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        team.external.Payment payment = new team.external.Payment();
        // mappings goes here
        payment.setOrderId(ordered.getOrderId());
        OrderApplication.applicationContext
            .getBean(team.external.PaymentService.class)
            .requestPayment(payment);

        OrderCanceled orderCanceled = new OrderCanceled(this);
        orderCanceled.publishAfterCommit();
    }

    @PreRemove
    public void onPreRemove(){
        OrderCanceled orderCanceled = new OrderCanceled();
        BeanUtils.copyProperties(this, orderCanceled);
        orderCanceled.publishAfterCommit();
    }

    public static OrderRepository repository() {
        OrderRepository orderRepository = OrderApplication.applicationContext.getBean(
            OrderRepository.class
        );
        return orderRepository;
    }
}
