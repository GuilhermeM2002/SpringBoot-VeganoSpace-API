package br.com.restaurante.VeganoSpace.application.dto.menu;

import br.com.restaurante.VeganoSpace.core.domain.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MenuDto(
        Long id,
        @NotBlank
        String dish,
        @NotBlank
        double price,
        @NotBlank
        String details,
        @NotNull
        Category category
) { }
