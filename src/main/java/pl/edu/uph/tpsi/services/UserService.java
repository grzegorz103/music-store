package pl.edu.uph.tpsi.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import pl.edu.uph.tpsi.dto.UserDTO;
import pl.edu.uph.tpsi.models.User;

public interface UserService extends UserDetailsService
{
        User create ( UserDTO userDTO );

        User getByUsername(String username);

        boolean isLoginCorrect ( String login, String password );
}
