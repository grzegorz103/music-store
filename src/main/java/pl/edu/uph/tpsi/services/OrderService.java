package pl.edu.uph.tpsi.services;

import pl.edu.uph.tpsi.dto.OrderDTO;
import pl.edu.uph.tpsi.models.Order;

import java.util.List;

public interface OrderService {
    List<OrderDTO> findAll(String username);

    public Order findById(Long id);

    public Order create(Order disc);

    public Order updateById(Long id);

    public void delete(Long id);
}
