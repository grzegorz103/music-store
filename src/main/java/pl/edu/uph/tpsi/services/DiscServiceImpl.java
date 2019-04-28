package pl.edu.uph.tpsi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.uph.tpsi.models.Disc;
import pl.edu.uph.tpsi.repositories.DiscRepository;

import java.util.List;

@Service ("discService")
public class DiscServiceImpl implements DiscService
{
        private final DiscRepository discRepository;

        @Autowired
        public DiscServiceImpl ( DiscRepository discRepository )
        {
                this.discRepository = discRepository;
        }

        @Override
        public List<Disc> findAll ()
        {
                return discRepository.findAll();
        }

        @Override
        public Disc findById ( Long id )
        {
                if ( id != null && id < 0 )
                        return discRepository.findById( 0L ).orElse( null );
                return discRepository.findById( id ).orElse( null );
        }

        @Override
        public Long create ( Disc disc )
        {
                return null;
        }

        @Override
        public Disc update ( Long id, Disc disc )
        {
                return null;
        }

        @Override
        public void delete ( Long id )
        {

        }
}
