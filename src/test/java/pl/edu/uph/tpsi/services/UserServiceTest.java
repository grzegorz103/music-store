package pl.edu.uph.tpsi.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.edu.uph.tpsi.dto.AddressDTO;
import pl.edu.uph.tpsi.dto.UserDTO;
import pl.edu.uph.tpsi.models.User;
import pl.edu.uph.tpsi.models.UserRole;
import pl.edu.uph.tpsi.repositories.RoleRepository;
import pl.edu.uph.tpsi.repositories.UserRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith (MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceTest
{
        @Spy
        private UserRepository userRepository;

        @Spy
        private BCryptPasswordEncoder encoder;

        @Mock
        private RoleRepository roleRepository;

        @InjectMocks
        private UserServiceImpl userService;

        @Test
        public void loadUserByUsernameTest ()
        {
                User user = User.builder().username( "test" ).build();
                when( userRepository.findUserByUsername( "test" ) ).thenReturn( user );
      //          assertThat( userService.loadUserByUsername( "test" ) ).isEqualTo( user );
        }

      //  @Test (expected = UsernameNotFoundException.class)
        //public void loadUserByUsernameEmptyTest ()
     //   {
       //         userService.loadUserByUsername( "noSuchUserInDB" );
        //}

        @Test
        public void createUserTest ()
        {
                User test = User.builder()
                        .username( "testLogin" )
                        .password( "testPassword" )
                        .build();
                when( roleRepository.findUserRoleByUserType( UserRole.UserType.ROLE_USER ) ).thenReturn( new UserRole( 0L, UserRole.UserType.ROLE_USER ) );
                when( userRepository.save( any( User.class ) ) ).thenReturn( test );
                UserDTO userDTO = new UserDTO( "testLogin", "testPassword", "testPassword", "email@email.pl"  );
                User createdUser = userService.create( userDTO );
                assertThat( createdUser.getUsername() ).isEqualTo( userDTO.getUsername() );
        }
}
