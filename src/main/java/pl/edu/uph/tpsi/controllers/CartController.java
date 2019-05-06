package pl.edu.uph.tpsi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.uph.tpsi.dto.CartDTO;
import pl.edu.uph.tpsi.models.Cart;
import pl.edu.uph.tpsi.models.CartItem;
import pl.edu.uph.tpsi.models.Disc;
import pl.edu.uph.tpsi.services.CartService;

import javax.servlet.http.HttpSession;
import java.util.Base64;

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
        public CartDTO getCart ( @RequestHeader ("Authorization") String auth )
        {
                String authToken = auth.substring( "Basic".length() ).trim();
                String username = new String( Base64.getDecoder()
                        .decode( authToken ) ).split( ":" )[0];
                return new CartDTO( cartService.getCart(username).getList() );
        }

        @PostMapping
        public CartDTO addToCart ( @RequestHeader ("Authorization") String auth,
                                   @RequestParam (name = "id", required = false, defaultValue = "1") Disc disc,
                                   @RequestParam (name = "amount", required = false, defaultValue = "1") Long amount )
        {
                String authToken = auth.substring( "Basic".length() ).trim();
                String username = new String( Base64.getDecoder()
                        .decode( authToken ) ).split( ":" )[0];
                cartService.addToCart( username, disc, amount );
                return new CartDTO( cartService.getCart( username ).getList() );
        }

        @PutMapping
        public void makeOrder ( @RequestHeader ("Authorization") String auth )
        {
                String authToken = auth.substring( "Basic".length() ).trim();
                String username = new String( Base64.getDecoder()
                        .decode( authToken ) ).split( ":" )[0];

                cartService.makeOrder( username );
        }
}
