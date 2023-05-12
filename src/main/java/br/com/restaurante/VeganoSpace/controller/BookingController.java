package br.com.restaurante.VeganoSpace.controller;

import br.com.restaurante.VeganoSpace.controller.services.BookingValidation;
import br.com.restaurante.VeganoSpace.domain.DTO.booking.BookingData;
import br.com.restaurante.VeganoSpace.domain.DTO.booking.BookingDataQuery;
import br.com.restaurante.VeganoSpace.domain.DTO.booking.BookingDataOutput;
import br.com.restaurante.VeganoSpace.domain.DTO.booking.BookingDataUpdate;
import br.com.restaurante.VeganoSpace.domain.DTO.mapper.BookingMapper;
import br.com.restaurante.VeganoSpace.domain.Booking;
import br.com.restaurante.VeganoSpace.domain.repository.BookingRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
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
    private BookingRepository repository;
    @Autowired
    private BookingMapper bookingMapper;

    @Autowired
    private BookingValidation bookingValidation;
    @PostMapping
    @Transactional
    public ResponseEntity persist (@RequestBody @Valid BookingData data, UriComponentsBuilder uriBilder){
        var booking = new Booking(data);

        var bookingValid = bookingValidation.validation(booking);

        var uri = uriBilder.path("booking/{id}").buildAndExpand(bookingValid.getId()).toUri();

        return ResponseEntity.created(uri).body(new BookingDataOutput(bookingValid));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity update (@RequestBody @Valid BookingDataUpdate data, @PathVariable Long id){
        var dataEntity = bookingMapper.toEntity(data);

        var booking = repository.getReferenceById(id);
        booking.BookingUpdate(dataEntity);

        var bookingDto = bookingMapper.toModel(booking);
        return ResponseEntity.ok(bookingDto);
    }

    @GetMapping
    public ResponseEntity<Page<BookingDataQuery>> findAll (@PageableDefault(size = 10) Pageable page){
        var bookingList = repository.findAll(page).map(BookingDataQuery::new);

        return ResponseEntity.ok(bookingList);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete (@PathVariable Long id){
        var bookingDelete = repository.getReferenceById(id);
        repository.delete(bookingDelete);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDataQuery> findOne (@PathVariable Long id){
        var booking = repository.getReferenceById(id);

        return ResponseEntity.ok(new BookingDataQuery(booking));
    }
}
