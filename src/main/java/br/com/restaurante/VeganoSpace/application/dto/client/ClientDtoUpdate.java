package br.com.restaurante.VeganoSpace.application.dto.client;

import br.com.restaurante.VeganoSpace.application.dto.user.UserDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ClientDtoUpdate(
        @NotNull
        Long id,
        @NotBlank
        String name,
        @NotNull
        LocalDate dateBirth,
        @NotNull
        UserDto user
) {
}
