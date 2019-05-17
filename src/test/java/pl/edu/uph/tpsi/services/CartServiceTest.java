package pl.edu.uph.tpsi.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pl.edu.uph.tpsi.exceptions.EmptyCartException;
import pl.edu.uph.tpsi.models.Cart;
import pl.edu.uph.tpsi.models.Disc;
import pl.edu.uph.tpsi.models.Order;
import pl.edu.uph.tpsi.models.User;
import pl.edu.uph.tpsi.repositories.CartRepository;
import pl.edu.uph.tpsi.repositories.OrderRepository;

import java.util.ArrayList;
import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith (MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CartServiceTest
{
        @Spy
        private Cart cart;

        @Mock
        private OrderServiceImpl orderService;

        @InjectMocks
        private CartServiceImpl cartService;

        @Mock
        private CartRepository cartRepository;

        @Before
        public void setup ()
        {
                Disc disc = new Disc( 1L, "TestBrand", "Test", new Date( new Date().getTime() - 10 ), 1f, 100, false, new ArrayList<String>(), "" );
                cartService.addToCart( "test", disc, 4L );
        }

        @Test
        public void addDiscToCartTest ()
        {
                User test = User.builder().username( "test" ).build();
                // when( cartRepository.findByUser( any(User.class) ) ).t(test);
                assertThat( cart.getList().size() ).isEqualTo( 1 );
                assertThat( cart.getList().get( 0 ).getDisc().getBand() ).isEqualTo( "TestBrand" );
                assertThat( cart.getList().get( 0 ).getAmount() ).isEqualTo( 4L );
        }

        @Test
        public void removeByIdTest ()
        {
                cartService.removeById( "test", 1L );
                assertThat( cart.getList().size() ).isEqualTo( 0L );
        }

        @Test
        public void makeOrderTest ()
        {
                Order order = new Order();
                order.setDiscs( cart.getList() );
                when( orderService.create( any( Order.class ) ) ).thenReturn( order );
                assertThat( cartService.makeOrder( "test" ).getDiscs() ).isEqualTo( order.getDiscs() );
        }

        @Test (expected = EmptyCartException.class)
        public void makeOrderWithEmptyCartTest ()
        {
                cart.setList( new ArrayList<>() );
                cartService.makeOrder( "test" );
        }
}
