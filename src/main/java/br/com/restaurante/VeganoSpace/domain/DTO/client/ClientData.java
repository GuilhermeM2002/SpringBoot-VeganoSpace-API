package br.com.restaurante.VeganoSpace.domain.DTO.client;

import br.com.restaurante.VeganoSpace.domain.DTO.user.UserData;
import br.com.restaurante.VeganoSpace.domain.User;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record ClientData(
        @NotBlank
        String cpf,
        @NotBlank
        String name,
        @NotNull
        @Past
        LocalDate dateBirth,
        @NotNull
        UserData user
) {
}
