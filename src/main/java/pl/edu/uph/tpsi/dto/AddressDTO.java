package pl.edu.uph.tpsi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.uph.tpsi.models.Address;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddressDTO {
    private String city;
    private String street;
    private String postCode;
    private String houseNumber;
    private String name;
    private String surname;
    private String phoneNumber;

    public AddressDTO(Address address) {
        this.city = address.getCity();
        this.street = address.getStreet();
        this.postCode = address.getPostCode();
        this.houseNumber = address.getHouseNumber();
        this.name = address.getName();
        this.surname = address.getSurname();
        this.phoneNumber = address.getPhoneNumber();
    }
}
