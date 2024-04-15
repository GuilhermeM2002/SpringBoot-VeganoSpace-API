package br.com.restaurante.VeganoSpace.application.dto.client;

import br.com.restaurante.VeganoSpace.application.dto.user.UserDto;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record ClientDto(
        @NotBlank
        String cpf,
        @NotBlank
        String name,
        @NotNull
        @Past
        LocalDate dateBirth,
        @NotNull
        UserDto user
) {
}
