package br.com.restaurante.VeganoSpace.controller.services;

import br.com.restaurante.VeganoSpace.domain.Booking;
import br.com.restaurante.VeganoSpace.domain.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class BookingValidation {
    @Autowired
    private BookingRepository repository;
    public Booking validation(Booking booking){
        var tables = repository.findByTable();

        return Optional.of(booking.getTable())
                .filter(table -> !tables.contains(table) || !tables.isEmpty())
                .map(table -> {
                    booking.setCode(codeGenerator());
                    repository.save(booking);
                    return booking;
                }).orElseThrow(() -> new RuntimeException("Table already reserved or no tables available, choose another"));
    }

    private String codeGenerator(){

        var random = new Random();

        var length = 10;

        return random.ints(0, 36)
                .limit(length)
                .mapToObj(i -> i < 10 ? String.valueOf(i) : String.valueOf(((char) i+55)))
                .collect(Collectors.joining());
    }
}
