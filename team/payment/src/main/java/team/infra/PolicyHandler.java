package team.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import team.config.kafka.KafkaProcessor;
import team.domain.*;
import java.util.List;

@Service
@Transactional
public class PolicyHandler {

    @Autowired
    PaymentRepository paymentRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrderCanceled_CancelPayment(@Payload OrderCanceled orderCanceled) {
        if (!orderCanceled.validate()) return;
        OrderCanceled event = orderCanceled;
        System.out.println(
            "\n\n##### listener CancelPayment : " +
            orderCanceled.toJson() +
            "\n\n"
        );

        if(orderCanceled.validate()){
            List<Payment> paymentList = paymentRepository.findByOrderId(orderCanceled.getOrderId());
            if ((paymentList != null) && !paymentList.isEmpty()){
                paymentRepository.deleteAll(paymentList);
            }
        }
    }
    // keep

}
