package pl.edu.uph.tpsi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.uph.tpsi.models.UserRole;

public interface RoleRepository extends JpaRepository<UserRole, Long> {
    UserRole findUserRoleByUserType(UserRole.UserType userType);
}
