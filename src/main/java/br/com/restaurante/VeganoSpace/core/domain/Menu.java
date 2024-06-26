package br.com.restaurante.VeganoSpace.core.domain;

import br.com.restaurante.VeganoSpace.application.dto.menu.MenuDto;
import br.com.restaurante.VeganoSpace.application.dto.menu.MenuDtoUpdate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "T_MENU")
@Entity(name = "T_MENU")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dish;
    private double price;
    private String details;
    @Enumerated(EnumType.STRING)
    private Category category;

    public Menu(MenuDto data){
        this.dish = data.dish();
        this.price = data.price();
        this.details = data.details();
        this.category = data.category();
    }

    public void menuUpdate(MenuDtoUpdate data){
        if (data.dish() != null){
            this.dish = data.dish();
        }
        if (data.price() > 0){
            this.price = data.price();
        }
        if (data.details() != null){
            this.details = data.details();
        }
        if (data.category() != null){
            this.category = data.category();
        }
    }
}
