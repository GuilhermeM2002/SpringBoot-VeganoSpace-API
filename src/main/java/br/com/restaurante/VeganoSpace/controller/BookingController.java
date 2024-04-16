package br.com.restaurante.VeganoSpace.controller;

import br.com.restaurante.VeganoSpace.services.BookingService;
import br.com.restaurante.VeganoSpace.services.BookingValidation;
import br.com.restaurante.VeganoSpace.application.dto.booking.BookingDto;
import br.com.restaurante.VeganoSpace.application.dto.booking.BookingDtoQuery;
import br.com.restaurante.VeganoSpace.application.dto.booking.BookingDtoOutput;
import br.com.restaurante.VeganoSpace.application.dto.booking.BookingDtoUpdate;
import br.com.restaurante.VeganoSpace.application.dto.mapper.BookingMapper;
import br.com.restaurante.VeganoSpace.core.domain.Booking;
import br.com.restaurante.VeganoSpace.adapters.repository.BookingRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("booking")
@SecurityRequirement(name = "bearer-key")
public class BookingController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private BookingService service;
    @PostMapping
    @Transactional
    public ResponseEntity persist (@RequestBody @Valid BookingDto dto, UriComponentsBuilder uriBilder){
        var booking = service.persistBooking(dto);
        var uri = uriBilder.path("booking/{id}").buildAndExpand(booking.id()).toUri();

        return ResponseEntity.created(uri).body(
                new BookingDtoOutput(mapper.map(booking, Booking.class)));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity update (@RequestBody @Valid BookingDtoUpdate dto, @PathVariable Long id){
        var booking = service.updateBooking(dto, id);

        return ResponseEntity.ok(booking);
    }

    @GetMapping
    public ResponseEntity<Page<BookingDtoQuery>> findAll (@PageableDefault(size = 10) Pageable page){
        var bookingList = service.findALlBooking(page);

        return ResponseEntity.ok(bookingList);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete (@PathVariable Long id){
        service.deleteBooking(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDtoQuery> findOne (@PathVariable Long id){
        var booking = service.findOneBooking(id);

        return ResponseEntity.ok(
                new BookingDtoQuery(mapper.map(booking, Booking.class)));
    }
}
