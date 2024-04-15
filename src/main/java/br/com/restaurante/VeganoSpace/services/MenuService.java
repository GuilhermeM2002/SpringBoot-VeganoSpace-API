package br.com.restaurante.VeganoSpace.services;

import br.com.restaurante.VeganoSpace.core.domain.Category;
import br.com.restaurante.VeganoSpace.core.domain.Menu;
import br.com.restaurante.VeganoSpace.adapters.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MenuService {
    @Autowired
    private MenuRepository repository;

    public ResponseEntity<Page<Menu>> findByCategory(Category category, Pageable page){
        var dishList = repository.findByCategory(category, page);

        return ResponseEntity.ok(dishList);
    }

    public ResponseEntity<Page<Menu>> findByPrice(double price, Pageable page){
        var dishList = repository.findByPrice(price, page);

        return ResponseEntity.ok(dishList);
    }
}
