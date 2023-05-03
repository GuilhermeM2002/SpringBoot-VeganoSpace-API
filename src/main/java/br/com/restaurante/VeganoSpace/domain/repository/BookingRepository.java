package br.com.restaurante.VeganoSpace.domain.repository;

import br.com.restaurante.VeganoSpace.domain.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
