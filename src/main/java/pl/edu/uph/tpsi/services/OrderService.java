package pl.edu.uph.tpsi.services;

import pl.edu.uph.tpsi.models.Disc;
import pl.edu.uph.tpsi.models.Order;

import java.util.List;

public interface OrderService
{
        public List<Order> findAll ();

        public Order findById ( Long id );

        public Order create ( Order disc );

        public Order update ( Long id, Order disc );

        public void delete ( Long id );
}
