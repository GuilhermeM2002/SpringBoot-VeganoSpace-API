package br.com.restaurante.VeganoSpace.controller;

import br.com.restaurante.VeganoSpace.domain.Client;
import br.com.restaurante.VeganoSpace.domain.DTO.client.ClientData;
import br.com.restaurante.VeganoSpace.domain.DTO.client.ClientDataOutput;
import br.com.restaurante.VeganoSpace.domain.DTO.user.UserData;
import br.com.restaurante.VeganoSpace.domain.repository.ClientRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ClientControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private ClientRepository repository;
    @Autowired
    private JacksonTester<ClientData> clientDataJacksonTester;
    @Autowired
    private  JacksonTester<ClientDataOutput> clientDataOutputJacksonTester;

    @Test
    @DisplayName("Devolver o código http 400 quando as informações forem invalidas")
    @WithMockUser
    void cod400() throws Exception {
        var response = mvc.perform(post("/client/sign-up")).andReturn().getResponse();

        assertThat(response.getStatus())
                .isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Devolver o código http 200 quando as informações forem validas")
    @WithMockUser
    void cod200() throws Exception {
        var client = new ClientData(
                "65892475615",
                "Guilherme Martins Ribeiro",
                LocalDate.of(2002, 6, 8),
                user());

        when(repository.save(any())).thenReturn(new Client(client));

        var response = mvc.perform(post("/client/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(clientDataJacksonTester.write(client).getJson())).andReturn().getResponse();

        var clientOutput = new ClientDataOutput(
                client.cpf(),
                client.name(),
                client.dateBirth()
        );

        var jsonExpected = clientDataOutputJacksonTester.write(clientOutput).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonExpected);
    }

    private UserData user(){
        return new UserData(
                "guilherme@gmail.com",
                "guilherme"
        );
    }

}