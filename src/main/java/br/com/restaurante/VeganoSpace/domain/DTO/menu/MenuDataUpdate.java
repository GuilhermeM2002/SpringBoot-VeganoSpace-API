package br.com.restaurante.VeganoSpace.domain.DTO.menu;

import br.com.restaurante.VeganoSpace.domain.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MenuDataUpdate(
        @NotBlank
        String dish,
        @NotNull
        double price,
        @NotBlank
        String details,
        @NotNull
        Category category
) { }
