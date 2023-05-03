package br.com.restaurante.VeganoSpace.domain.DTO.user;

import jakarta.validation.constraints.NotBlank;

public record UserData(
        @NotBlank
        String email,
        @NotBlank
        String password
) {
}
