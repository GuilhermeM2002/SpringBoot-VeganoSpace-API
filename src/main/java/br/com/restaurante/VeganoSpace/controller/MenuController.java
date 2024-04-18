package br.com.restaurante.VeganoSpace.controller;

import br.com.restaurante.VeganoSpace.application.dto.menu.MenuDto;
import br.com.restaurante.VeganoSpace.application.dto.menu.MenuDtoOutput;
import br.com.restaurante.VeganoSpace.application.dto.menu.MenuDtoQuery;
import br.com.restaurante.VeganoSpace.application.dto.menu.MenuDtoUpdate;
import br.com.restaurante.VeganoSpace.services.MenuService;
import br.com.restaurante.VeganoSpace.core.domain.Category;
import br.com.restaurante.VeganoSpace.core.domain.Menu;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService service;
    @Autowired
    private ModelMapper mapper;

    @PostMapping
    public ResponseEntity saveMenu(@RequestBody @Valid MenuDto dto, UriComponentsBuilder uriBuilder){
        var menu = service.persistMenu(dto);
        var uri = uriBuilder.path("/menu/{id}").buildAndExpand(dto.id()).toUri();

        return ResponseEntity.created(uri).body(mapper.map(menu, MenuDtoOutput.class));
    }

    @PutMapping
    public ResponseEntity updateMenu(@RequestBody @Valid MenuDtoUpdate dto){
        var menu = service.updateMenu(dto);

        return ResponseEntity.ok(menu);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMenu(@PathVariable Long id){
        service.deleteMenu(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<MenuDtoQuery>> menuListAll(@PageableDefault(size = 5, sort = {"dish"}) Pageable page){
        var dishList = service.findAllMenu(page);

        return ResponseEntity.ok(dishList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuDtoQuery> menuListOne(@PathVariable Long id){
        var dish = service.findOneMenu(id);

        return ResponseEntity.ok(new MenuDtoQuery(mapper.map(dish, Menu.class)));
    }
    
    @GetMapping("/price/{price}")
    public ResponseEntity<Page<MenuDtoQuery>> getByPrice(@PathVariable double price, Pageable page){
        var menuList = service.findByPrice(price, page);

        return ResponseEntity.ok(menuList);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<Page<MenuDtoQuery>> getByCategory(@PathVariable Category category, Pageable page){
        var menuList = service.findByCategory(category, page);

        return ResponseEntity.ok(menuList);
    }
}
