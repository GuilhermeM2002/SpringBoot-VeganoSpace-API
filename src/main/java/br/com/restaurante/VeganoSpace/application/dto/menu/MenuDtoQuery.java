package br.com.restaurante.VeganoSpace.application.dto.menu;

import br.com.restaurante.VeganoSpace.core.domain.Category;
import br.com.restaurante.VeganoSpace.core.domain.Menu;

public record MenuDtoQuery(Long id, String dish, double price, String details, Category category) {
    public MenuDtoQuery(Menu menu){
        this(menu.getId(), menu.getDish(), menu.getPrice(), menu.getDetails(), menu.getCategory());
    }
}
