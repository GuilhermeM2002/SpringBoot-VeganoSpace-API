package br.com.restaurante.VeganoSpace.domain.repository;

import br.com.restaurante.VeganoSpace.domain.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query("""
            select b.table from T_BOOKING b
            """)
    List<String> findByTable();
}
