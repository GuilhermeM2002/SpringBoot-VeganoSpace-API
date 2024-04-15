package br.com.restaurante.VeganoSpace.application.dto.menu;

import br.com.restaurante.VeganoSpace.core.domain.Category;
import br.com.restaurante.VeganoSpace.core.domain.Menu;

public record MenuDtoOutput(String dish, double price, String details, Category category) {
    public MenuDtoOutput(Menu menu){
        this(menu.getDish(), menu.getPrice(), menu.getDetails(), menu.getCategory());
    }
}
