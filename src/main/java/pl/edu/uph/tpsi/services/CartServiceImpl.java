package pl.edu.uph.tpsi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.uph.tpsi.exceptions.EmptyCartException;
import pl.edu.uph.tpsi.models.Cart;
import pl.edu.uph.tpsi.models.CartItem;
import pl.edu.uph.tpsi.models.Disc;
import pl.edu.uph.tpsi.models.Order;
import pl.edu.uph.tpsi.repositories.OrderRepository;

import java.util.Date;
import java.util.stream.Collectors;

@Service ("cartService")
public class CartServiceImpl implements CartService
{
        private final Cart cart;

        private final OrderService orderService;

        @Autowired
        public CartServiceImpl ( Cart cart, OrderService orderService )
        {
                this.cart = cart;
                this.orderService = orderService;
        }

        @Override
        public void addToCart ( Disc disc, Long amount )
        {
                CartItem cartItem = cart.getList()
                        .stream()
                        .filter( e -> e.getDisc().getID().equals( disc.getID() ) )
                        .findFirst()
                        .orElse( null );
                if ( cartItem == null )
                        cart.getList().add( new CartItem( disc, amount ) );
                else
                        cartItem.setAmount( cartItem.getAmount() + amount );
        }

        @Override
        public boolean removeById ( Long id )
        {
                cart.setList( cart.getList()
                        .stream()
                        .filter( e -> !e.getDisc().getID().equals( id ) ).collect( Collectors.toList() )
                );
                return true;
        }

        @Override
        public boolean remove ( Disc id )
        {
                return false;
        }

        @Override
        public Order makeOrder ()
        {
                if ( cart.getList().size() == 0 )
                        throw new EmptyCartException();
                Order order = Order.builder()
                        .discs( cart.getList() )
                        .orderDate( new Date() )
                        .build();
                if ( order != null )
                {
                        return orderService.create( order );
                }
                return null;
        }
}
