package br.com.restaurante.VeganoSpace.domain.DTO.menu;

import br.com.restaurante.VeganoSpace.domain.Category;
import br.com.restaurante.VeganoSpace.domain.Menu;

public record MenuDataOutput(String dish, double price, String details, Category category) {
    public MenuDataOutput(Menu menu){
        this(menu.getDish(), menu.getPrice(), menu.getDetails(), menu.getCategory());
    }
}
