package takbaejm;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DeliveryboardRepository extends CrudRepository<Deliveryboard, Long> {

    List<Deliveryboard> findByRequestId(Long requestId);


}