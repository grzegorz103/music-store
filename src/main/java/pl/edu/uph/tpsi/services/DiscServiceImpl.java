package pl.edu.uph.tpsi.services;

import org.springframework.stereotype.Service;
import pl.edu.uph.tpsi.models.Disc;

import java.util.List;

@Service ("discService")
public class DiscServiceImpl implements DiscService
{

        @Override
        public List<Disc> findAll ()
        {
                return null;
        }

        @Override
        public Disc findById ( Long id )
        {
                return null;
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
