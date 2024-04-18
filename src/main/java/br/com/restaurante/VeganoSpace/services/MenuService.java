package br.com.restaurante.VeganoSpace.services;

import br.com.restaurante.VeganoSpace.application.dto.menu.MenuDto;
import br.com.restaurante.VeganoSpace.application.dto.menu.MenuDtoQuery;
import br.com.restaurante.VeganoSpace.application.dto.menu.MenuDtoUpdate;
import br.com.restaurante.VeganoSpace.core.domain.Category;
import br.com.restaurante.VeganoSpace.core.domain.Menu;
import br.com.restaurante.VeganoSpace.adapters.repository.MenuRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MenuService {
    @Autowired
    private MenuRepository repository;
    @Autowired
    private ModelMapper mapper;

    public MenuDto persistMenu(MenuDto dto){
        var menu = mapper.map(dto, Menu.class);
        repository.save(menu);

        return mapper.map(menu, MenuDto.class);
    }

    public MenuDtoUpdate updateMenu(MenuDtoUpdate dto){
        var menu = repository.getReferenceById(dto.id());
        menu.menuUpdate(dto);
        repository.save(menu);

        return mapper.map(menu, MenuDtoUpdate.class);
    }

    public void deleteMenu(Long id){
        var menu = repository.getReferenceById(id);
        repository.delete(menu);
    }

    public Page<MenuDtoQuery> findAllMenu(Pageable page){
        var dishList = repository.findAll(page).map(MenuDtoQuery::new);

        return dishList;
    }

    public MenuDtoQuery findOneMenu(Long id){
        var dish = repository.getReferenceById(id);

        return mapper.map(dish, MenuDtoQuery.class);
    }

    public Page<MenuDtoQuery> findByCategory(Category category, Pageable page){
        var dishList = repository.findByCategory(category, page).map(MenuDtoQuery::new);

        return dishList;
    }

    public Page<MenuDtoQuery> findByPrice(double price, Pageable page){
        var dishList = repository.findByPrice(price, page).map(MenuDtoQuery::new);

        return dishList;
    }
}
