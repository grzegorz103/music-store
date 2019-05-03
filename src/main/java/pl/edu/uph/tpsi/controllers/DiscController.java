package pl.edu.uph.tpsi.controllers;

import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import pl.edu.uph.tpsi.models.Disc;
import pl.edu.uph.tpsi.services.DiscService;

import java.util.List;

@RestController
@RequestMapping ("/api/disc")
public class DiscController
{
        private final DiscService discService;

        @Autowired
        public DiscController ( DiscService discService )
        {
                this.discService = discService;
        }

        @GetMapping
        public List<Disc> findAll ()
        {
                return discService.findAll();
        }

        @GetMapping ("/{id}")
        public Disc findById ( @PathVariable ("id") Long id )
        {
                return discService.findById( id );
        }

        @PostMapping
        @ResponseStatus (HttpStatus.CREATED)
        public Long create ( @RequestBody Disc disc )
        {
                Preconditions.checkNotNull( disc );
                return discService.create( disc );
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
