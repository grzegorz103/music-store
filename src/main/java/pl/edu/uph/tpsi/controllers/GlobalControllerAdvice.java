package pl.edu.uph.tpsi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.edu.uph.tpsi.exceptions.EmptyCartException;
import pl.edu.uph.tpsi.exceptions.UserDetailsException;

@ControllerAdvice
public class GlobalControllerAdvice
{
        @ExceptionHandler (EmptyCartException.class)
        protected ResponseEntity<?> emptyCartHandler ( Exception e )
        {
                return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST );
        }

        @ExceptionHandler (UserDetailsException.class)
        protected ResponseEntity<?> userDetailsHandler ( Exception e )
        {
                return new ResponseEntity<>( e.getMessage(), HttpStatus.FORBIDDEN );
        }
}
