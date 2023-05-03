package br.com.restaurante.VeganoSpace.domain.DTO.client;

import br.com.restaurante.VeganoSpace.domain.DTO.user.UserData;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ClientDataUpdate(
        @NotNull
        Long id,
        @NotBlank
        String name,
        @NotNull
        LocalDate dateBirth,
        @NotNull
        UserData user
) {
}
