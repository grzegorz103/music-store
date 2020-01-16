package pl.edu.uph.tpsi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.uph.tpsi.models.Cart;
import pl.edu.uph.tpsi.models.User;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUser(User user);
}
