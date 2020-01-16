package pl.edu.uph.tpsi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.uph.tpsi.exceptions.EmptyCartException;
import pl.edu.uph.tpsi.exceptions.UserDetailsException;
import pl.edu.uph.tpsi.models.*;
import pl.edu.uph.tpsi.repositories.CartRepository;
import pl.edu.uph.tpsi.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Grzegorz Piłat
 */
@Service("cartService")
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;

    private final OrderService orderService;

    private final UserRepository userRepository;

    private final AddressService addressService;

    private final DiscService discService;

    @Value("${cart.empty.exception}")
    private String cartEmptyException;

    @Value("${user.details.exception")
    private String userDetailsException;

    @Autowired
    public CartServiceImpl(OrderService orderService, CartRepository cartRepository, UserRepository userRepository, AddressService addressService, DiscService discService) {
        this.orderService = orderService;
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.addressService = addressService;
        this.discService = discService;
    }

    /**
     * Creates cart for specified user
     *
     * @param user for whom the cart will be created
     */
    @Override
    @Transactional
    public void create(User user) {
        if (cartRepository.findByUser(userRepository.findUserByUsername(user.getUsername())) == null) {
            cartRepository.save(Cart.builder()
                    .list(new ArrayList<>())
                    .user(user)
                    .build()
            );
        }
    }

    /**
     * Adds item to shopping cart.
     * <p>If an item already exists in the cart, its amount will be increased</p>
     * <p>Otherwise the item will be added to cart</p>
     *
     * @param username username of the shopping cart owner
     * @param disc     item that will be added to the cart
     * @param amount   amount of item
     */
    @Override
    @Transactional
    public void addToCart(String username, Disc disc, Integer amount) {
        Cart cart = cartRepository.findByUser(userRepository.findUserByUsername(username));
        if (cart != null) {
            CartItem cartItem = cart.getList()
                    .stream()
                    .filter(e -> e.getDisc().getID().equals(disc.getID()))
                    .findFirst()
                    .orElse(null);
            if (cartItem == null) {
                cart.getList().add(new CartItem(disc, amount));
            } else
                cartItem.setAmount(cartItem.getAmount() + amount);
            cartRepository.save(cart);
        }
    }

    /**
     * Removes item from cart by given ID
     *
     * @param username username of the shopping cart owner
     * @param id       ID of item that will be deleted
     * @return true if item has been removed from cart
     */
    @Override
    public boolean removeById(String username, Long id) {
        Cart cart = cartRepository.findByUser(userRepository.findUserByUsername(username));
        if (cart != null) {
            cart.setList(cart.getList()
                    .stream()
                    .filter(e -> !e.getID().equals(id))
                    .collect(Collectors.toList())
            );
            cartRepository.save(cart);
        }
        return true;
    }

    @Override
    public boolean remove(Cart cart) {
        if (cart != null) {
            cartRepository.delete(cart);
            return true;
        }
        return false;
    }

    /**
     * Creates order by user's shopping cart and clears it's cart
     *
     * @param username username of the shopping cart owner
     * @return created order
     * @throws EmptyCartException   if user's cart contains no items
     * @throws UserDetailsException if user's address is not filled correctly
     */
    @Override
    @Transactional
    public Order makeOrder(String username) {
        if (!addressService.isAddressCorrect(userRepository.findUserByUsername(username).getAddress()))
            throw new UserDetailsException(this.userDetailsException);
        Cart cart = cartRepository.findByUser(userRepository.findUserByUsername(username));
        if (cart != null) {
            if (cart.getList().isEmpty())
                throw new EmptyCartException(this.cartEmptyException);

            Order order = Order.builder()
                    .discs(cart.getList())
                    .orderDate(new Date())
                    .user(userRepository.findUserByUsername(username))
                    .build();

            discService.validateOrder(order);
            discService.decreaseAmount(order);
            if (order != null) {
                cart.setList(new ArrayList<>());
                cartRepository.save(cart);
                return orderService.create(order);
            }
        }
        return null;
    }

    /**
     * Retrieves user's shopping cart
     *
     * @param username username of the shopping cart owner
     * @return user's shopping cart
     */
    @Override
    public Cart getCart(String username) {
        Cart cart = cartRepository.findByUser(userRepository.findUserByUsername(username));
        if (cart != null) {
            return cart;
        }

        return null;
    }
}