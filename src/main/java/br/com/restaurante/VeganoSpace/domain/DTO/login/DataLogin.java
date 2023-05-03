package br.com.restaurante.VeganoSpace.domain.DTO.login;

import jakarta.validation.constraints.NotBlank;

public record DataLogin(
        @NotBlank
        String email,
        @NotBlank
        String password) {
}
