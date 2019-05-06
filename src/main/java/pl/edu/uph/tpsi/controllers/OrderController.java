package pl.edu.uph.tpsi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.uph.tpsi.dto.OrderDTO;
import pl.edu.uph.tpsi.models.Order;
import pl.edu.uph.tpsi.services.OrderService;

import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping ("/api/order")
@CrossOrigin (origins = "http://localhost:4200")
public class OrderController
{
        private final OrderService orderService;

        @Autowired
        public OrderController ( OrderService orderService )
        {
                this.orderService = orderService;
        }

        @GetMapping
        public List<OrderDTO> getOrders ( @RequestHeader ("Authorization") String auth )
        {
                String authToken = auth.substring( "Basic".length() ).trim();
                String username = new String( Base64.getDecoder()
                        .decode( authToken ) ).split( ":" )[0];

                return orderService.findAll( username );
        }
}
