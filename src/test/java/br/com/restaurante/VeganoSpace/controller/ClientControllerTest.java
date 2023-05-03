package br.com.restaurante.VeganoSpace.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

class ClientControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Devolver o código http 400 quando as informações forem invalidas")
    @WithMockUser
    void persist() throws Exception {
        var response = mvc.perform(post("/client")).andReturn().getResponse();

        assertThat(response.getStatus())
                .isEqualTo(HttpStatus.BAD_REQUEST.value());
    }


}