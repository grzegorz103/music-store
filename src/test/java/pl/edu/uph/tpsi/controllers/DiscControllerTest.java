package pl.edu.uph.tpsi.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.edu.uph.tpsi.models.Disc;
import pl.edu.uph.tpsi.services.DiscService;
import pl.edu.uph.tpsi.services.DiscServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith (SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DiscControllerTest
{
        @Autowired
        private MockMvc mockMvc;

        private DiscService discService;

        @Before
        public void initTests ()
        {
                this.discService = new DiscServiceImpl();
                List<Disc> list = new ArrayList<>();
                list.add( new Disc( 1L, "Brand1", new Date( new Date().getTime() - 10 ), 1f, 2, false ) );
                list.add( new Disc( 2L, "Brand2", new Date( new Date().getTime() - 10 ), 2f, 3, false ) );
                list.add( new Disc( 3L, "Brand3", new Date( new Date().getTime() - 10 ), 3f, 4, false ) );
                when( discService.findAll() ).thenReturn( list );
        }

        @Test
        public void findAllDiscsTest ()
        {
        //        mockMvc.
        }
}
