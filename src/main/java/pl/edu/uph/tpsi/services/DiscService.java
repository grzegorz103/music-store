package pl.edu.uph.tpsi.services;

import pl.edu.uph.tpsi.dto.DiscDTO;
import pl.edu.uph.tpsi.models.Disc;

import java.util.List;

public interface DiscService
{
        List<DiscDTO> findAll ();

        DiscDTO findById ( Long id );

        Long create ( DiscDTO disc );

        DiscDTO update ( Long id, DiscDTO disc );

        boolean delete ( Long id );
}
