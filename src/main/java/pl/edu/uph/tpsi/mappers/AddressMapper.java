package pl.edu.uph.tpsi.mappers;

import org.mapstruct.Mapper;
import pl.edu.uph.tpsi.dto.AddressDTO;
import pl.edu.uph.tpsi.models.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressDTO addressToDTO(Address address);

    Address DTOtoAddress(AddressDTO addressDTO);
}
