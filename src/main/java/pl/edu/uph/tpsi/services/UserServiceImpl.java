package pl.edu.uph.tpsi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.uph.tpsi.dto.UserDTO;
import pl.edu.uph.tpsi.models.User;
import pl.edu.uph.tpsi.models.UserRole;
import pl.edu.uph.tpsi.repositories.RoleRepository;
import pl.edu.uph.tpsi.repositories.UserRepository;

import java.util.Collections;

@Service ("userService")
public class UserServiceImpl implements UserService
{
        private final UserRepository userRepository;

        private final PasswordEncoder encoder;

        private final RoleRepository roleRepository;

        private final CartService cartService;

        @Autowired
        public UserServiceImpl ( UserRepository userRepository, PasswordEncoder encoder, RoleRepository roleRepository, CartService cartService )
        {
                this.userRepository = userRepository;
                this.encoder = encoder;
                this.roleRepository = roleRepository;
                this.cartService = cartService;
        }

        @Override
        public UserDetails loadUserByUsername ( String s ) throws UsernameNotFoundException
        {
                User user = userRepository.findUserByUsername( s );

                if ( user == null )
                        throw new UsernameNotFoundException( "User does not exists" );

                return user;
        }

        @Override
        @Transactional
        public User create ( UserDTO userDTO )
        {
                User user = User.builder()
                        .username( userDTO.getUsername() )
                        .email( userDTO.getEmail() )
                        .enabled( true )
                        .password( encoder.encode( userDTO.getPassword() ) )
                        .userRoles( Collections.singleton( roleRepository.findUserRoleByUserType( UserRole.UserType.ROLE_USER ) ) )
                        .build();
                userRepository.save( user );
                cartService.create( user );
                return user;
        }

        @Override
        public boolean isLoginCorrect ( String login, String password )
        {
                User u = userRepository.findUserByUsername( login );
                if ( u == null )
                {
                        return false;
                }
                return u.getUsername().equals( login )
                        && encoder.matches( u.getUsername(), encoder.encode( password ) );
        }

}
