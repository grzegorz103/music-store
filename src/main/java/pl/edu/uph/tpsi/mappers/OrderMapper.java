package pl.edu.uph.tpsi.mappers;

import org.mapstruct.Mapper;
import pl.edu.uph.tpsi.dto.OrderDTO;
import pl.edu.uph.tpsi.models.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order DTOtoOrder(OrderDTO orderDTO);

    OrderDTO orderToDTO(Order order);
}
