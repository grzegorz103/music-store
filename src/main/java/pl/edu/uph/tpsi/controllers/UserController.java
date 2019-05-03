package pl.edu.uph.tpsi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.edu.uph.tpsi.dto.UserDTO;
import pl.edu.uph.tpsi.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Base64;

@RestController
@RequestMapping ("/api/users")
public class UserController
{
        private final UserService userService;

        @Autowired
        public UserController ( UserService userService )
        {
                this.userService = userService;
        }

        @PostMapping
        @ResponseStatus (HttpStatus.CREATED)
        public void addUser ( @RequestBody @Valid UserDTO userDTO,
                              BindingResult bindingResult )
        {
                if ( bindingResult.hasErrors() )
                        return;
                userService.create( userDTO );
        }

        @RequestMapping ("/login")
        public boolean login ( @RequestBody UserDTO userDTO )
        {
                return userService.isLoginCorrect( userDTO.getUsername(), userDTO.getPassword() );
        }

        @RequestMapping ("/user")
        public Principal user ( HttpServletRequest request )
        {
                String authToken = request.getHeader( "Authorization" )
                        .substring( "Basic".length() ).trim();
                return () -> new String( Base64.getDecoder()
                        .decode( authToken ) ).split( ":" )[0];
        }
}