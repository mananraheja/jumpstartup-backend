package com.jumpstartup.jumpstartupjava;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumpstartup.Database.LoginDatabase;
import com.jumpstartup.Encryption.PasswordEncryption;
import com.jumpstartup.LoginBody.LoginRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class LoginControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private LoginController loginController;

    @Mock
    private LoginDatabase loginDatabase;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
    }

    @Test
    public void testLogin() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk());
    }

    @Test
    public void testLoginSubmit() throws Exception {
        LoginRequest loginRequest = new LoginRequest("username", "password", "email", "type");
        PasswordEncryption encryption = new PasswordEncryption();
        loginRequest.setHashpass(encryption.encryptPassword(loginRequest.getHashpass()));
        when(loginDatabase.authenticate(anyString(), anyString())).thenReturn(true);

        MvcResult result = mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(loginRequest)))
                .andReturn();

        assertThat(result.getResponse().getContentAsString()).isEqualTo("AUTHORIZED");
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void testSignupSubmit() throws Exception {
        LoginRequest loginRequest = new LoginRequest("username", "password", "email", "type");
        PasswordEncryption encryption = new PasswordEncryption();
        loginRequest.setHashpass(encryption.encryptPassword(loginRequest.getHashpass()));
        when(loginDatabase.newUser(anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn(true);

        MvcResult result = mockMvc.perform(post("/login/signup")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(loginRequest)))
                .andReturn();

        assertThat(result.getResponse().getContentAsString()).isEqualTo("SIGNUP SUCCESSFUL");
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}
