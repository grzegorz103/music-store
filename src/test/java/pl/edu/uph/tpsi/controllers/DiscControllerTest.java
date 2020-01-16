package pl.edu.uph.tpsi.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.edu.uph.tpsi.config.UserAuthentication;
import pl.edu.uph.tpsi.dto.DiscDTO;
import pl.edu.uph.tpsi.models.Disc;
import pl.edu.uph.tpsi.services.DiscService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DiscControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private DiscService discService;

    private UserAuthentication userAuthentication;

    @InjectMocks
    private DiscController discController;

    private List<DiscDTO> list;


    @Before
    public void setup() {
        list = new ArrayList<>();
        list.add(new DiscDTO(1L, "Brand1", "Title1", "", LocalDate.of(1888, 11, 11), 1f, 2, false, new ArrayList<String>(), null));
        list.add(new DiscDTO(2L, "Brand2", "Title2", "", LocalDate.of(1888, 11, 11), 2f, 3, false, new ArrayList<String>(), null));
        list.add(new DiscDTO(3L, "Brand3", "Title3", "", LocalDate.of(1888, 11, 11), 3f, 4, false, new ArrayList<String>(), null));
        when(discService.findAll()).thenReturn(list);
    }

    @Test
    @WithMockUser(username = "test", password = "test")
    public void findAllDiscsTest() throws Exception {
        mockMvc.perform(get("/api/disc"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteDiscByIdTest() {
        when(discService.delete(1L)).thenReturn(false);
        when(discService.delete(2L)).thenReturn(true);
        assertThat(discController.delete(1L)).isEqualTo(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        assertThat(discController.delete(2L)).isEqualTo(new ResponseEntity<>(HttpStatus.OK));
    }

    @Test
    public void createDiscTestWithoutAuth() throws Exception {
        DiscDTO d = new DiscDTO(10L, "band", "test", "", null, 1f, 1, false, new ArrayList<String>(), null);
        mockMvc.perform(post("/api/disc")
                .content(new ObjectMapper().writeValueAsString(d))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void createDiscTestWithAuth() throws Exception {
        userAuthentication = mock(UserAuthentication.class);
        discService = mock(DiscService.class);
        discController = new DiscController(discService, userAuthentication);
        mockMvc = MockMvcBuilders.standaloneSetup(discController).build();
        when(userAuthentication.hasAdminRole(any(String.class))).thenReturn(true);
        DiscDTO d = new DiscDTO(10L, "band", "test", "", LocalDate.of(1888, 11, 11), 1f, 1, false, new ArrayList<String>(), null);
        when(discService.create(any(DiscDTO.class))).thenReturn(1L);
        mockMvc.perform(post("/api/disc")
                .content(new ObjectMapper().writeValueAsString(d))
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Basic a2pramtqOmtqa2prag=="))
                .andExpect(status().isBadRequest());
    }
}
