package pl.edu.uph.tpsi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.uph.tpsi.models.OrderStatus;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {
    OrderStatus findByOrOrderType(OrderStatus.OrderType orderType);
}
