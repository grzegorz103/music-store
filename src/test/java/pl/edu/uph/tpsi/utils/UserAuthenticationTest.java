package pl.edu.uph.tpsi.utils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import pl.edu.uph.tpsi.config.UserAuthentication;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith (MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserAuthenticationTest
{
        @Spy
        private UserAuthentication userAuthentication;

        @Before
        public void setup ()
        {
                when( (( User ) SecurityContextHolder.getContext()
                        .getAuthentication().getPrincipal()).getUsername() ).thenReturn( "testuser" );
        }

        @Test
        public void getUsernameTest ()
        {
                assertThat( userAuthentication.getUsername() ).isEqualTo( "testuser" );
        }
}
