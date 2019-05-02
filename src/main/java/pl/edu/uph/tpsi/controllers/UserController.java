package pl.edu.uph.tpsi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.uph.tpsi.dto.UserDTO;
import pl.edu.uph.tpsi.services.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping ("/users")
public class UserController
{
        private final UserService userService;

        @Autowired
        public UserController ( UserService userService )
        {
                this.userService = userService;
        }

        @PostMapping
        public void addUser ( @RequestBody @Valid UserDTO userDTO,
                              BindingResult bindingResult )
        {
                if ( bindingResult.hasErrors() )
                        return;
                userService.create( userDTO );
        }
}