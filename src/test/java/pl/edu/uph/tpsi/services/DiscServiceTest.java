package pl.edu.uph.tpsi.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.uph.tpsi.dto.DiscDTO;
import pl.edu.uph.tpsi.models.Disc;
import pl.edu.uph.tpsi.repositories.DiscRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static junit.framework.TestCase.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith (MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DiscServiceTest
{
        @Mock
        private DiscRepository discRepository;

        @InjectMocks
        private DiscServiceImpl discService;

        private List<Disc> list;

        @Before
        @SuppressWarnings ("all")
        public void setup ()
        {
                list = new ArrayList<>();
                list.add( new Disc( 1L, "Brand1", "Title1", LocalDate.of( 1888, 11, 11 ), 1f, 2, false, new ArrayList<String>(), "", null ) );
                list.add( new Disc( 2L, "Brand2", "Title2", LocalDate.of( 1888, 11, 11 ), 2f, 3, false, new ArrayList<String>(), "", null ) );
                list.add( new Disc( 3L, "Brand3", "Title3", LocalDate.of( 1888, 11, 11 ), 3f, 4, false, new ArrayList<String>(), "", null ) );
                list.add( new Disc( 10L, "Brand4", "Title4", LocalDate.of( 1888, 11, 11 ), 3f, 4, false, new ArrayList<String>(), "", null ) );

                when( discRepository.findAll() ).thenReturn( list );
                when( discRepository.findById( 0L ) ).thenReturn( Optional.of( list.get( 0 ) ) );
        }

        @Test
        public void findAllDiscsTest ()
        {
                List<DiscDTO> temp = list.stream().map( DiscDTO::new ).collect( Collectors.toList() );
                assertThat( discService.findAll() ).isEqualTo( temp );
        }

        @Test
        public void findOneDiscTest ()
        {
                assertThat( discService.findById( 0L ) ).isEqualTo( list.get( 0 ) );
                assertNull( discService.findById( null ) );
                assertThat( discService.findById( -1L ) ).isEqualTo( list.get( 0 ) );
        }

        @Test
        public void createDiscTest ()
        {
                DiscDTO disc = new DiscDTO( 4L, "TestBrand", "TestTitle", "", LocalDate.of( 1888, 11, 11 ), 1f, 1, false, new ArrayList<String>(), null );
                // list.add( disc );
                // when( discRepository.save( disc ) ).thenReturn( disc );
                assertThat( discService.create( disc ) ).isEqualTo( disc.getID() );
                assertEquals( 5, discRepository.findAll().size() );
        }

        @Test
        public void updateDiscTest ()
        {
                DiscDTO test = new DiscDTO( 0L, "ReplaceBand", "Test", "", LocalDate.of( 1888, 11, 11 ), 100f, 1, false, new ArrayList<String>(), null );
                DiscDTO test2 = new DiscDTO( 111L, "NoIdInDB", "Test", "", LocalDate.of( 1888, 11, 11 ), 100f, 1, false, new ArrayList<String>(), null );
                assertThat( discService.update( 0L, test ).getBand() ).isEqualTo( "ReplaceBand" );
                assertNull( discService.update( 111L, test2 ) );
        }

        @Test
        public void deleteDiscByIdTest ()
        {
                when( discRepository.existsById( 10L ) ).thenReturn( true );
                assertThat( discService.delete( 10L ) ).isEqualTo( true );
                when( discRepository.existsById( 9L ) ).thenReturn( false ); // nie ma takiego mocka z id 9
                assertThat( discService.delete( 9L ) ).isEqualTo( false );
        }
}
