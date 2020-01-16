package pl.edu.uph.tpsi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.uph.tpsi.models.CartItem;
import pl.edu.uph.tpsi.models.Order;
import pl.edu.uph.tpsi.models.OrderStatus;
import pl.edu.uph.tpsi.models.User;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long ID;
    private List<CartItem> discs;
    private Date orderDate;
    private User user;
    private Integer orderID;
    private OrderStatus orderStatus;

    public OrderDTO(Order order) {
        this.ID = order.getID();
        this.discs = order.getDiscs();
        this.orderDate = order.getOrderDate();
        this.user = order.getUser();
        this.orderStatus = order.getOrderStatus();
    }
}
