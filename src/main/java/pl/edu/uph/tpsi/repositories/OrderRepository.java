package pl.edu.uph.tpsi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.uph.tpsi.models.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    boolean existsByOrderID(Integer id);
}
