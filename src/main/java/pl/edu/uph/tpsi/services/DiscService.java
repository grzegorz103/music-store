package pl.edu.uph.tpsi.services;

import pl.edu.uph.tpsi.models.Disc;

import java.util.List;

public interface DiscService
{
        public List<Disc> findAll ();

        public Disc findById ( Long id );

        public Long create ( Disc disc );

        public Disc update ( Long id, Disc disc );

        public void delete ( Long id );
}
