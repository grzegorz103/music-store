package pl.edu.uph.tpsi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.uph.tpsi.models.Cart;
import pl.edu.uph.tpsi.models.CartDTO;
import pl.edu.uph.tpsi.models.CartItem;
import pl.edu.uph.tpsi.models.Disc;
import pl.edu.uph.tpsi.services.CartService;

import java.util.List;

@RestController
@RequestMapping ("/cart")
public class CartController
{
        private final CartService cartService;

        @Autowired
        public CartController ( CartService cartService )
        {
                this.cartService = cartService;
        }

        @GetMapping
        public CartDTO getCart ()
        {
                return new CartDTO( cartService.getCart().getList() );
        }

        @PostMapping
        public CartDTO addToCart ( @RequestParam ("id") Disc disc,
                                   @RequestParam ("amount") Long amount )
        {
                cartService.addToCart( disc, amount );
                return new CartDTO( cartService.getCart().getList() );
        }

        @PutMapping
        public void makeOrder ()
        {
                cartService.makeOrder();
        }
}
