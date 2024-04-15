package br.com.restaurante.VeganoSpace.application.dto.user;

import jakarta.validation.constraints.NotBlank;

public record UserDto(
        @NotBlank
        String email,
        @NotBlank
        String password
) {
}
