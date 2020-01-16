package pl.edu.uph.tpsi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.uph.tpsi.models.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
