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
import pl.edu.uph.tpsi.models.Disc;
import pl.edu.uph.tpsi.repositories.DiscRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertNull;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

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
        public void setUp ()
        {
                list = new ArrayList<>();
                list.add( new Disc( 1L, "Brand1", new Date( new Date().getTime() - 10 ), 1f, 2, false ) );
                list.add( new Disc( 2L, "Brand2", new Date( new Date().getTime() - 10 ), 2f, 3, false ) );
                list.add( new Disc( 3L, "Brand3", new Date( new Date().getTime() - 10 ), 3f, 4, false ) );

                when( discRepository.findAll() ).thenReturn( list );
                when( discRepository.findById( 0L ) ).thenReturn( Optional.of( list.get( 0 ) ) );
        }

        @Test
        public void findAllDiscsTest ()
        {
                assertThat( discService.findAll() ).isEqualTo( list );
        }

        @Test
        public void findOneDiscTest ()
        {
                assertThat( discService.findById( 0L ) ).isEqualTo( list.get( 0 ) );
                assertNull( discService.findById( null ) );
                assertThat( discService.findById( -1L ) ).isEqualTo( list.get( 0 ) );
        }
}
