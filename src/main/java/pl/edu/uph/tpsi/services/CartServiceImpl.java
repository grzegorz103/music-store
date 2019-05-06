package pl.edu.uph.tpsi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.uph.tpsi.exceptions.EmptyCartException;
import pl.edu.uph.tpsi.models.*;
import pl.edu.uph.tpsi.repositories.CartRepository;
import pl.edu.uph.tpsi.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

@Service ("cartService")
public class CartServiceImpl implements CartService
{
        private final CartRepository cartRepository;

        private final OrderService orderService;

        private final UserRepository userRepository;

        @Autowired
        public CartServiceImpl ( OrderService orderService, CartRepository cartRepository, UserRepository userRepository )
        {
                this.orderService = orderService;
                this.cartRepository = cartRepository;
                this.userRepository = userRepository;
        }

        @Override
        @Transactional
        public void create ( User user )
        {
                if ( cartRepository.findByUser( userRepository.findUserByUsername( user.getUsername() ) ) == null )
                {
                        cartRepository.save( Cart.builder()
                                .list( new ArrayList<>() )
                                .user( user )
                                .build()
                        );
                }
        }

        @Override
        @Transactional
        public void addToCart ( String username, Disc disc, Long amount )
        {
                Cart cart = cartRepository.findByUser( userRepository.findUserByUsername( username ) );
                if ( cart != null )
                {
                        CartItem cartItem = cart.getList()
                                .stream()
                                .filter( e -> e.getDisc().getID().equals( disc.getID() ) )
                                .findFirst()
                                .orElse( null );
                        if ( cartItem == null )
                        {
                                cart.getList().add( new CartItem( disc, amount ) );
                        } else
                                cartItem.setAmount( cartItem.getAmount() + amount );
                        cartRepository.save( cart );
                }
        }

        @Override
        public boolean removeById ( String username, Long id )
        {
                Cart cart = cartRepository.findByUser( userRepository.findUserByUsername( username ) );
                if ( cart != null )
                {
                        cart.setList( cart.getList()
                                .stream()
                                .filter( e -> !e.getID().equals( id ) )
                                .collect( Collectors.toList() )
                        );
                        cartRepository.save( cart );
                }
                return true;
        }

        @Override
        public boolean remove ( Disc id )
        {
                return false;
        }

        @Override
        @Transactional
        public Order makeOrder ( String username )
        {
                Cart cart = cartRepository.findByUser( userRepository.findUserByUsername( username ) );
                if ( cart != null )
                {
                        if ( cart.getList().size() == 0 )
                                throw new EmptyCartException();

                        Order order = Order.builder()
                                .discs( cart.getList() )
                                .orderDate( new Date() )
                                .user( userRepository.findUserByUsername( username ) )
                                .build();
                        if ( order != null )
                        {
                                cart.setList( new ArrayList<>() );
                                cartRepository.save( cart );
                                return orderService.create( order );
                        }
                }
                return null;
        }

        @Override
        public Cart getCart ( String username )
        {
                Cart cart = cartRepository.findByUser( userRepository.findUserByUsername( username ) );
                if ( cart != null )
                {
                        return cart;
                }
                return null;
        }
}
