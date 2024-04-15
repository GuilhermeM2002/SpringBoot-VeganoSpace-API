package br.com.restaurante.VeganoSpace.application.dto.login;

import jakarta.validation.constraints.NotBlank;

public record LoginDto(
        @NotBlank
        String email,
        @NotBlank
        String password) {
}
