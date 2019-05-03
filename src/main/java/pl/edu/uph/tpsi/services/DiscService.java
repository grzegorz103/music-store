package pl.edu.uph.tpsi.services;

import pl.edu.uph.tpsi.models.Disc;

import java.util.List;

public interface DiscService
{
        List<Disc> findAll ();

        Disc findById ( Long id );

        Long create ( Disc disc );

        Disc update ( Long id, Disc disc );

        boolean delete ( Long id );
}
