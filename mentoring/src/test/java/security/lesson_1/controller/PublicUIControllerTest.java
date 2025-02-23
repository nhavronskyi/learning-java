package security.lesson_1.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import security.lesson_1.request.AuthRequest;
import security.lesson_1.request.AuthResponse;
import security.lesson_1.service.AuthService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class PublicUIControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AuthService authService;

    @InjectMocks
    private PublicUIController publicUIController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(publicUIController).build();
    }

    @Test
    void testHello() throws Exception {
        mockMvc.perform(get("/public/hello")).andExpect(status().isOk()).andExpect(content().string("Hello, World!"));
    }

    @Test
    void testLogin() throws Exception {
        AuthResponse authResponse = new AuthResponse("token", HttpStatus.OK);
        when(authService.login(any(AuthRequest.class))).thenReturn(authResponse);

        mockMvc.perform(post("/public/login").contentType(MediaType.APPLICATION_JSON).content("{\"username\":\"username\",\"password\":\"password\"}")).andExpect(status().isOk()).andExpect(content().json("{\"token\":\"token\",\"status\":\"OK\"}"));

        verify(authService, times(1)).login(any(AuthRequest.class));
    }

    @Test
    void testRegister() throws Exception {
        AuthResponse authResponse = new AuthResponse("token", HttpStatus.OK);
        when(authService.register(any(AuthRequest.class))).thenReturn(authResponse);

        mockMvc.perform(post("/public/register").contentType(MediaType.APPLICATION_JSON).content("{\"username\":\"username\",\"password\":\"password\"}")).andExpect(status().isOk()).andExpect(content().json("{\"token\":\"token\"}"));

        verify(authService, times(1)).register(any(AuthRequest.class));
    }
}
