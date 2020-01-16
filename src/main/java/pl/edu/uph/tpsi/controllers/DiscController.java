package pl.edu.uph.tpsi.controllers;

import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.uph.tpsi.config.UserAuthentication;
import pl.edu.uph.tpsi.dto.DiscDTO;
import pl.edu.uph.tpsi.services.DiscService;

@RestController
@RequestMapping("/api/disc")
@CrossOrigin(origins = {"http://localhost:4200", "https://music-store-2620.firebaseapp.com/"})
public class DiscController {
    private final DiscService discService;

    private final UserAuthentication userAuthentication;


    @Autowired
    public DiscController(DiscService discService, UserAuthentication userAuthentication) {
        this.discService = discService;
        this.userAuthentication = userAuthentication;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(discService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public DiscDTO findById(@PathVariable("id") Long id) {
        return discService.findById(id);
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> create(@RequestBody DiscDTO disc) {
        //     if ( !userAuthentication.hasAdminRole( auth ) )
        //           return new ResponseEntity<>( HttpStatus.UNAUTHORIZED );
        return new ResponseEntity<>(discService.create(disc), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public DiscDTO update(@PathVariable("id") Long id,
                          @RequestBody DiscDTO disc) {
        Preconditions.checkNotNull(disc);
        return discService.update(id, disc);
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return new ResponseEntity<>(discService.delete(id) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
}
