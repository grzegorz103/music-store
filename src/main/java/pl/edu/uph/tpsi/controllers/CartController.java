package pl.edu.uph.tpsi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.uph.tpsi.dto.CartDTO;
import pl.edu.uph.tpsi.models.Cart;
import pl.edu.uph.tpsi.models.CartItem;
import pl.edu.uph.tpsi.models.Disc;
import pl.edu.uph.tpsi.services.CartService;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping ("/api/cart")
@CrossOrigin (origins = "http://localhost:4200")
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
                for ( CartItem x : cartService.getCart().getList() )
                        System.out.println( x.getDisc().getBand() );
                return new CartDTO( cartService.getCart().getList() );
        }

        @PostMapping
        public CartDTO addToCart ( @RequestParam (name = "id", required = false, defaultValue = "1") Disc disc,
                                   @RequestParam (name = "amount", required = false, defaultValue = "1") Long amount )
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
