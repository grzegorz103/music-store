package pl.edu.uph.tpsi.mappers;

import org.mapstruct.Mapper;
import pl.edu.uph.tpsi.dto.DiscDTO;
import pl.edu.uph.tpsi.models.Disc;

@Mapper(componentModel = "spring")
public interface DiscMapper {
    DiscDTO discToDTO(Disc disc);

    Disc DTOtoDisc(DiscDTO discDTO);
}
