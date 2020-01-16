package pl.edu.uph.tpsi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.edu.uph.tpsi.dto.OrderDTO;
import pl.edu.uph.tpsi.mappers.OrderMapper;
import pl.edu.uph.tpsi.models.Cart;
import pl.edu.uph.tpsi.models.CartItem;
import pl.edu.uph.tpsi.models.Order;
import pl.edu.uph.tpsi.models.OrderStatus;
import pl.edu.uph.tpsi.repositories.OrderRepository;
import pl.edu.uph.tpsi.repositories.OrderStatusRepository;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    private final Random generator;

    private final OrderStatusRepository orderStatusRepository;

    @Value("#{new Integer('${order.id.range}')}")
    private Integer ORDER_ID_RANGE;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            OrderMapper orderMapper,
                            Random generator,
                            OrderStatusRepository orderStatusRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.generator = generator;
        this.orderStatusRepository = orderStatusRepository;
    }

    @Override
    public List<OrderDTO> findAll(String username) {
        if (username != null && !username.isEmpty())
            return orderRepository.findAll()
                    .stream()
                    .filter(e -> e.getUser().getUsername().equals(username))
                    .map(orderMapper::orderToDTO)
                    .collect(Collectors.toList());
        return null;
    }

    @Override
    public Order findById(Long id) {
        return null;
    }

    @Override
    public Order create(Order order) {
        if (order != null) {
            order.setOrderStatus(orderStatusRepository.findByOrOrderType(OrderStatus.OrderType.ORDERED));
            order.setOrderID(generateID());
            return orderRepository.save(order);
        }
        return null;
    }

    @Override
    public Order updateById(Long id) {
        Order o = orderRepository.findById(id).orElse(null);
        if (o != null) {
            OrderStatus.OrderType status = o.getOrderStatus().getOrderType();
            switch (status) {
                case CANCELED:
                    o.setOrderStatus(orderStatusRepository.findByOrOrderType(OrderStatus.OrderType.DONE));
                    break;
                case DONE:
                    o.setOrderStatus(orderStatusRepository.findByOrOrderType(OrderStatus.OrderType.ORDERED));
                    break;
                default:
                    o.setOrderStatus(orderStatusRepository.findByOrOrderType(OrderStatus.OrderType.CANCELED));
                    break;
            }
            orderRepository.save(o);
        }
        return o;
    }

    @Override
    public void delete(Long id) {

    }

    private Integer generateID() {
        Integer id = generator.nextInt(ORDER_ID_RANGE);
        while (orderRepository.existsByOrderID(id)) {
            id = generator.nextInt(ORDER_ID_RANGE);
        }
        return id;
    }
}
