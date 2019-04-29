package pl.edu.uph.tpsi.services;

import pl.edu.uph.tpsi.models.Cart;
import pl.edu.uph.tpsi.models.Disc;
import pl.edu.uph.tpsi.models.Order;

public interface CartService
{
        public void addToCart ( Disc disc, Long amount );

        public boolean removeById ( Long id );

        public boolean remove ( Disc id );

        public Order makeOrder ();

        public Cart getCart ();
}
