package pl.edu.uph.tpsi.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.edu.uph.tpsi.models.Disc;
import pl.edu.uph.tpsi.services.DiscService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith (SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DiscControllerTest
{
        @Autowired
        private MockMvc mockMvc;

        @Mock
        private DiscService discService;

        @InjectMocks
        private DiscController discController;

        private List<Disc> list;

        @Before
        public void setup ()
        {
                list = new ArrayList<>();
                list.add( new Disc( 1L, "Brand1", new Date( new Date().getTime() - 10 ), 1f, 2, false ) );
                list.add( new Disc( 2L, "Brand2", new Date( new Date().getTime() - 10 ), 2f, 3, false ) );
                list.add( new Disc( 3L, "Brand3", new Date( new Date().getTime() - 10 ), 3f, 4, false ) );
                when( discService.findAll() ).thenReturn( list );
        }

        @Test
        @WithMockUser (username = "test", password = "test")
        public void findAllDiscsTest () throws Exception
        {
                mockMvc.perform( get( "/disc" ) )
                        .andExpect( status().isOk() );
                assertThat( discController.findAll() ).isEqualTo( list );
        }

        @Test
        public void deleteDiscByIdTest ()
        {
                when( discService.delete( 1L ) ).thenReturn( false );
                when( discService.delete( 2L ) ).thenReturn( true );
                assertThat( discController.delete( 1L ) ).isEqualTo( new ResponseEntity<>( HttpStatus.NO_CONTENT ) );
                assertThat( discController.delete( 2L ) ).isEqualTo( new ResponseEntity<>( HttpStatus.OK ) );
        }
}
