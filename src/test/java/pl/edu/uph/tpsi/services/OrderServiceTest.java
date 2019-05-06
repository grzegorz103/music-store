package pl.edu.uph.tpsi.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import pl.edu.uph.tpsi.models.Order;
import pl.edu.uph.tpsi.repositories.OrderRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith (MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrderServiceTest
{
        @Mock
        private OrderRepository orderRepository;

        @InjectMocks
        private OrderServiceImpl orderService;

        @Mock
        private List<Order> list;

        @Before
        public void setup ()
        {
                when( orderRepository.findAll() ).thenReturn( list );
        }

        @Test
        public void findAll ()
        {

                //assertThat( orderService.findAll() ).isEqualTo( list );
        }

}
