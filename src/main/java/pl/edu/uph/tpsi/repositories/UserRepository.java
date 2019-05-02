package pl.edu.uph.tpsi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.uph.tpsi.models.User;

public interface UserRepository extends JpaRepository<User, Long>
{
        public User findUserByUsername ( String username );
}
