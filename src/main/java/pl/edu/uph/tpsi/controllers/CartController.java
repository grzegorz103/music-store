package pl.edu.uph.tpsi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.uph.tpsi.config.UserAuthentication;
import pl.edu.uph.tpsi.dto.CartDTO;
import pl.edu.uph.tpsi.models.Disc;
import pl.edu.uph.tpsi.services.CartService;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = {"http://localhost:4200", "https://music-store-2620.firebaseapp.com/"})
public class CartController {
    private final CartService cartService;

    private final UserAuthentication userAuthentication;

    @Autowired
    public CartController(CartService cartService, UserAuthentication userAuthentication) {
        this.cartService = cartService;
        this.userAuthentication = userAuthentication;
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public CartDTO getCart(@RequestHeader("Authorization") String auth) {
        return new CartDTO(cartService.getCart(userAuthentication.getUsername(auth)).getList());
    }

    @PostMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public CartDTO addToCart(@RequestHeader("Authorization") String auth,
                             @PathVariable(name = "id") Disc disc,
                             @RequestParam(name = "amount", required = false, defaultValue = "1") Integer amount) {
        String username = userAuthentication.getUsername(auth);
        cartService.addToCart(username, disc, amount);
        return new CartDTO(cartService.getCart(username).getList());
    }

    @PutMapping
    @PreAuthorize("isAuthenticated()")
    public void makeOrder(@RequestHeader("Authorization") String auth) {
        String username = userAuthentication.getUsername(auth);

        cartService.makeOrder(username);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public void deleteById(@RequestHeader("Authorization") String auth,
                           @PathVariable("id") Long id) {
        String username = userAuthentication.getUsername(auth);

        cartService.removeById(username, id);
    }
}
