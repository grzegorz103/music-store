package pl.edu.uph.tpsi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.edu.uph.tpsi.exceptions.EmptyCartException;
import pl.edu.uph.tpsi.exceptions.ItemOutOfStockException;
import pl.edu.uph.tpsi.exceptions.UserDetailsException;

import java.util.List;

@ControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler(EmptyCartException.class)
    protected ResponseEntity<?> emptyCartHandler(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserDetailsException.class)
    protected ResponseEntity<?> userDetailsHandler(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ItemOutOfStockException.class)
    protected ResponseEntity<List<String>> itemOutOfStockHandler(ItemOutOfStockException e) {
        return new ResponseEntity<>(e.getItems(), HttpStatus.PRECONDITION_FAILED);
    }
}
