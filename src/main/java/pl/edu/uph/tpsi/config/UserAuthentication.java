package pl.edu.uph.tpsi.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.edu.uph.tpsi.models.User;
import pl.edu.uph.tpsi.models.UserRole;

import java.util.List;

@Component
public class UserAuthentication
{
        public String getUsername ()
        {
                User u = ( User ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                return u.getUsername();
        }

        public boolean hasRole ( UserRole.UserType type )
        {
                User u = ( User ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                return u.getAuthorities()
                        .stream()
                        .anyMatch( e -> e.getAuthority().equals( type.name() ) );
        }
}
