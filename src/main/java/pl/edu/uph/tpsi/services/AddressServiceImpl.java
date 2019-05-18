package pl.edu.uph.tpsi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pl.edu.uph.tpsi.dto.AddressDTO;
import pl.edu.uph.tpsi.models.Address;
import pl.edu.uph.tpsi.models.User;
import pl.edu.uph.tpsi.repositories.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService
{
        private final AddressRepository addressRepository;

        @Autowired
        public AddressServiceImpl ( AddressRepository addressRepository )
        {
                this.addressRepository = addressRepository;
        }

        @Override
        public AddressDTO findOneByUser ( User user )
        {
                return new AddressDTO( user.getAddress() );
        }

        @Override
        public AddressDTO update ( Address target, AddressDTO addressDTO )
        {
                if ( target != null )
                {
                        target.setCity( addressDTO.getCity() );
                        target.setHouseNumber( addressDTO.getHouseNumber() );
                        target.setName( addressDTO.getName() );
                        target.setPhoneNumber( addressDTO.getPhoneNumber() );
                        target.setPostCode( addressDTO.getPostCode() );
                        target.setSurname( addressDTO.getSurname() );
                        target.setStreet( addressDTO.getStreet() );
                        addressRepository.save( target );
                        return addressDTO;
                }
                return null;
        }

        @Override
        public boolean isAddressCorrect ( Address address )
        {
                return !StringUtils.isEmpty( address.getCity() )
                        && !StringUtils.isEmpty( address.getHouseNumber() )
                        && !StringUtils.isEmpty( address.getName() )
                        && !StringUtils.isEmpty( address.getSurname() )
                        && !StringUtils.isEmpty( address.getPhoneNumber() )
                        && !StringUtils.isEmpty( address.getStreet() )
                        && !StringUtils.isEmpty( address.getClass() )
                        && !StringUtils.isEmpty( address.getPostCode() );
        }
}
