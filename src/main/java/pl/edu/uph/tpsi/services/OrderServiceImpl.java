package pl.edu.uph.tpsi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.uph.tpsi.dto.OrderDTO;
import pl.edu.uph.tpsi.mappers.OrderMapper;
import pl.edu.uph.tpsi.models.Cart;
import pl.edu.uph.tpsi.models.CartItem;
import pl.edu.uph.tpsi.models.Order;
import pl.edu.uph.tpsi.repositories.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service ("orderService")
public class OrderServiceImpl implements OrderService
{
        private final OrderRepository orderRepository;

        private final OrderMapper orderMapper;

        @Autowired
        public OrderServiceImpl ( OrderRepository orderRepository, OrderMapper orderMapper )
        {
                this.orderRepository = orderRepository;
                this.orderMapper = orderMapper;
        }

        @Override
        public List<OrderDTO> findAll ( String username )
        {
                if ( username != null && !username.isEmpty() )
                        return orderRepository.findAll()
                                .stream()
                                .filter( e -> e.getUser().getUsername().equals( username ) )
                                .map( orderMapper::orderToDTO )
                                .collect( Collectors.toList() );
                return null;
        }

        @Override
        public Order findById ( Long id )
        {
                return null;
        }

        @Override
        public Order create ( Order order )
        {
                if ( order != null )
                {
                        return orderRepository.save( order );
                }
                return null;
        }

        @Override
        public Order update ( Long id, Order disc )
        {
                return null;
        }

        @Override
        public void delete ( Long id )
        {

        }
}
