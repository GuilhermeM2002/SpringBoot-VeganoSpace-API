package br.com.restaurante.VeganoSpace.domain.DTO.menu;

import br.com.restaurante.VeganoSpace.domain.Category;
import br.com.restaurante.VeganoSpace.domain.Menu;

public record MenuDataQuery(Long id, String dish, double price, String details, Category category) {
    public MenuDataQuery(Menu menu){
        this(menu.getId(), menu.getDish(), menu.getPrice(), menu.getDetails(), menu.getCategory());
    }
}
