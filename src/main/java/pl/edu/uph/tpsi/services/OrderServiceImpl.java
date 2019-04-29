package pl.edu.uph.tpsi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.uph.tpsi.models.Cart;
import pl.edu.uph.tpsi.models.CartItem;
import pl.edu.uph.tpsi.models.Order;
import pl.edu.uph.tpsi.repositories.OrderRepository;

import java.util.List;

@Service ("orderService")
public class OrderServiceImpl implements OrderService
{
        private final OrderRepository orderRepository;

        @Autowired
        public OrderServiceImpl ( OrderRepository orderRepository )
        {
                this.orderRepository = orderRepository;
        }

        @Override
        public List<Order> findAll ()
        {
                return orderRepository.findAll();
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
