package pl.edu.uph.tpsi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.uph.tpsi.config.UserAuthentication;
import pl.edu.uph.tpsi.dto.OrderDTO;
import pl.edu.uph.tpsi.models.Order;
import pl.edu.uph.tpsi.services.OrderService;

import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/api/order")
@CrossOrigin(origins = {"http://localhost:4200", "https://music-store-2620.firebaseapp.com/"})
public class OrderController {
    private final OrderService orderService;

    private final UserAuthentication userAuthentication;

    @Autowired
    public OrderController(OrderService orderService, UserAuthentication userAuthentication) {
        this.orderService = orderService;
        this.userAuthentication = userAuthentication;
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<OrderDTO> getOrders(@RequestHeader("Authorization") String auth) {
        return orderService.findAll(userAuthentication.getUsername(auth));
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public Order updateById(@PathVariable Long id) {
        return orderService.updateById(id);
    }
}
