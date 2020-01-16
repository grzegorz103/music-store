package pl.edu.uph.tpsi.services;

import pl.edu.uph.tpsi.dto.AddressDTO;
import pl.edu.uph.tpsi.models.Address;
import pl.edu.uph.tpsi.models.User;

public interface AddressService {
    AddressDTO findOneByUser(User user);

    AddressDTO update(Address target, AddressDTO addressDTO);

    boolean isAddressCorrect(Address address);

    Address create(AddressDTO addressDTO);
}
