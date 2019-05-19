package pl.edu.uph.tpsi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pl.edu.uph.tpsi.dto.AddressDTO;
import pl.edu.uph.tpsi.models.Address;
import pl.edu.uph.tpsi.models.User;
import pl.edu.uph.tpsi.repositories.AddressRepository;

/**
 * @Author Grzegorz Piłat
 */
@Service
public class AddressServiceImpl implements AddressService
{
        private final AddressRepository addressRepository;

        @Autowired
        public AddressServiceImpl ( AddressRepository addressRepository )
        {
                this.addressRepository = addressRepository;
        }

        /**
         * Returns address bound to given user as parameter
         * @param user  user object whose address' has to be retrieved
         * @return      address as dto
         */
        @Override
        public AddressDTO findOneByUser ( User user )
        {
                return new AddressDTO( user.getAddress() );
        }

        /**
         * Replaces current address with new address details
         * @param target      address that has to be replaced
         * @param addressDTO  address which contains new values to replace
         * @return            updated address
         */
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

        /**
         * Checks if all fields of given address as parameter are not empty
         * @param address address that has to be validated
         * @return        true if all fields are not empty
         */
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
