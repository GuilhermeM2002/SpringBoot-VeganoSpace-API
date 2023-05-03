package br.com.restaurante.VeganoSpace.controller;

import br.com.restaurante.VeganoSpace.domain.DTO.menu.MenuDataQuery;
import br.com.restaurante.VeganoSpace.controller.services.MenuService;
import br.com.restaurante.VeganoSpace.domain.Category;
import br.com.restaurante.VeganoSpace.domain.Menu;
import br.com.restaurante.VeganoSpace.domain.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService service;
    @Autowired
    private MenuRepository repository;

    @GetMapping
    public ResponseEntity<Page<MenuDataQuery>> menuListAll(@PageableDefault(size = 5, sort = {"dish"}) Pageable page){
        var dishList = repository.findAll(page).map(MenuDataQuery::new);

        return ResponseEntity.ok(dishList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuDataQuery> menuListOne(@PathVariable Long id){
        var dish = repository.getReferenceById(id);

        return ResponseEntity.ok(new MenuDataQuery(dish));
    }
    
    @GetMapping("/price/{price}")
    public ResponseEntity<Page<Menu>> getByPrice(@PathVariable double price, Pageable page){
        return service.findByPrice(price, page);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<Page<Menu>> getByCategory(@PathVariable Category category, Pageable page){
        return service.findByCategory(category, page);
    }
}
