package br.com.restaurante.VeganoSpace.adapters.repository;

import br.com.restaurante.VeganoSpace.core.domain.Category;
import br.com.restaurante.VeganoSpace.core.domain.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface MenuRepository extends JpaRepository<Menu, Long> {
    @Query("""
            select m from T_MENU m where m.price <= :price
            """)
    Page<Menu> findByPrice(double price, Pageable page);

    @Query("""
            select m from T_MENU m where m.category = :category
            """)
    Page<Menu> findByCategory(Category category, Pageable page);
}
