package pl.edu.uph.tpsi.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import pl.edu.uph.tpsi.dto.UserDTO;
import pl.edu.uph.tpsi.models.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    User create(UserDTO userDTO);

    List<UserDTO> findAll();

    UserDTO update(Long id, UserDTO userDTO);

    void delete(Long id);

    User getByUsername(String username);

    UserDTO getCurrentUser();

    boolean isLoginCorrect(String login, String password);

}
