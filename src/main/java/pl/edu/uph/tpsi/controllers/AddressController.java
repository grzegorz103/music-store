package pl.edu.uph.tpsi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.uph.tpsi.config.UserAuthentication;
import pl.edu.uph.tpsi.dto.AddressDTO;
import pl.edu.uph.tpsi.models.Address;
import pl.edu.uph.tpsi.services.AddressService;
import pl.edu.uph.tpsi.services.UserService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "https://music-store-2620.firebaseapp.com/"})
public class AddressController {
    private final AddressService addressService;

    private final UserService userService;

    private final UserAuthentication userAuthentication;

    @Autowired
    public AddressController(AddressService addressService, UserService userService, UserAuthentication userAuthentication) {
        this.addressService = addressService;
        this.userService = userService;
        this.userAuthentication = userAuthentication;
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public AddressDTO findOneByUser(@RequestHeader("Authorization") String auth) {
        return addressService.findOneByUser(
                userService.getByUsername(userAuthentication.getUsername(auth))
        );
    }

    @PutMapping
    @PreAuthorize("isAuthenticated()")
    public AddressDTO update(@RequestBody AddressDTO addressDTO,
                             @RequestHeader("Authorization") String auth) {
        return addressService.update(
                userService.getByUsername(userAuthentication.getUsername(auth)).getAddress(),
                addressDTO
        );
    }
}
