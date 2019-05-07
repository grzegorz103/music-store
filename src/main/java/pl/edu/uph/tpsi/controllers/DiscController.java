package pl.edu.uph.tpsi.controllers;

import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.uph.tpsi.config.UserAuthentication;
import pl.edu.uph.tpsi.models.Disc;
import pl.edu.uph.tpsi.services.DiscService;

@RestController
@RequestMapping ("/api/disc")
@CrossOrigin (origins = "http://localhost:4200")
public class DiscController
{
        private final DiscService discService;

        private final UserAuthentication userAuthentication;

        @Autowired
        public DiscController ( DiscService discService, UserAuthentication userAuthentication )
        {
                this.discService = discService;
                this.userAuthentication = userAuthentication;
        }

        @GetMapping
        public ResponseEntity<?> findAll ( @RequestHeader ("Authorization") String auth )
        {
                return new ResponseEntity<>( discService.findAll(), HttpStatus.OK );
        }

        @GetMapping ("/{id}")
        public Disc findById ( @PathVariable ("id") Long id )
        {
                return discService.findById( id );
        }

        @PostMapping
        public ResponseEntity<?> create ( @RequestBody Disc disc,
                                          @RequestHeader ("Authorization") String auth )
        {
                if ( !userAuthentication.hasAdminRole( auth ) )
                        return new ResponseEntity<>( HttpStatus.UNAUTHORIZED );

                Preconditions.checkNotNull( disc );
                return new ResponseEntity<>( discService.create( disc ), HttpStatus.OK );
        }

        @PutMapping ("/{id}")
        @ResponseStatus (HttpStatus.OK)
        public void update ( @PathVariable ("id") Long id,
                             @RequestBody Disc disc )
        {
                Preconditions.checkNotNull( disc );
                discService.update( id, disc );
        }

        @DeleteMapping ("/{id}")
        public ResponseEntity<?> delete ( @PathVariable ("id") Long id )
        {
                return new ResponseEntity<>( discService.delete( id ) ? HttpStatus.OK : HttpStatus.NO_CONTENT );
        }
}
