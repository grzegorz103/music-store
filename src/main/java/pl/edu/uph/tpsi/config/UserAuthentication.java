package pl.edu.uph.tpsi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import pl.edu.uph.tpsi.models.UserRole;
import pl.edu.uph.tpsi.services.UserService;

import java.util.Base64;

/**
 * @author Grzegorz Piłat
 */
@Component
public class UserAuthentication {
    private final UserService userService;

    @Autowired
    public UserAuthentication(UserService userService) {
        this.userService = userService;
    }

    /**
     * Checks whether logged user has admin role
     *
     * @param auth Authorization request header
     * @return true if user is an admin
     */
    public boolean hasAdminRole(String auth) {
        if (!StringUtils.isEmpty(auth)) {
            String username = this.getUsername(auth);
            if (!StringUtils.isEmpty(username)) {
                return userService.getByUsername(username)
                        .getUserRoles()
                        .stream()
                        .anyMatch(e -> e.getUserType().equals(UserRole.UserType.ROLE_ADMIN));
            }
        }
        return false;
    }

    /**
     * Retrieves username from Authorization request header
     *
     * @param auth Authorization request header
     * @return username
     */
    public String getUsername(String auth) {
        String authToken = auth.substring("Basic".length()).trim();
        return new String(Base64.getDecoder()
                .decode(authToken))
                .split(":")[0];
    }
}
