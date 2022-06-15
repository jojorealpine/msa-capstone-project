package team.infra;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import team.domain.*;

public interface OrderDetailsRepository
    extends CrudRepository<OrderDetails, Long> {
    List<OrderDetails> findByorderId(Long orderId);
    List<OrderDetails> findByorderId(Long orderId);
    List<OrderDetails> findByorderId(Long orderId);
    List<OrderDetails> findByorderId(Long orderId);
    List<OrderDetails> findByorderId(Long orderId);
    // keep

}
