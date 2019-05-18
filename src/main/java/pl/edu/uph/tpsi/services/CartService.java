package pl.edu.uph.tpsi.services;

import pl.edu.uph.tpsi.models.Cart;
import pl.edu.uph.tpsi.models.Disc;
import pl.edu.uph.tpsi.models.Order;
import pl.edu.uph.tpsi.models.User;

public interface CartService
{
        void create ( User user );

        void addToCart ( String username, Disc disc, Integer amount );

        boolean removeById ( String username, Long id );

        boolean remove ( Disc id );

        public Order makeOrder ( String username );

        public Cart getCart (String username);
}
