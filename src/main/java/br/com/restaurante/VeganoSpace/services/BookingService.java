package br.com.restaurante.VeganoSpace.services;

import br.com.restaurante.VeganoSpace.adapters.repository.BookingRepository;
import br.com.restaurante.VeganoSpace.application.dto.booking.BookingDto;
import br.com.restaurante.VeganoSpace.application.dto.booking.BookingDtoQuery;
import br.com.restaurante.VeganoSpace.application.dto.booking.BookingDtoUpdate;
import br.com.restaurante.VeganoSpace.core.domain.Booking;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    @Autowired
    private BookingValidation validation;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private BookingRepository repository;

    public BookingDto persistBooking(BookingDto dto){
        var booking = new Booking(dto);

        return mapper.map(validation.validation(booking), BookingDto.class) ;
    }

    public BookingDtoUpdate updateBooking(BookingDtoUpdate dto, Long id){
        var booking = repository.getReferenceById(id);
        booking.BookingUpdate(mapper.map(dto, Booking.class));
        repository.save(booking);

        return mapper.map(booking, BookingDtoUpdate.class);
    }

    public Page<BookingDtoQuery> findALlBooking(Pageable page){
        var bookingList = repository.findAll(page).map(BookingDtoQuery::new);

        return bookingList;
    }

    public void deleteBooking(Long id){
        var bookingDelete = repository.getReferenceById(id);
        repository.delete(bookingDelete);
    }

    public BookingDtoQuery findOneBooking(Long id){
        var booking = repository.getReferenceById(id);
        return mapper.map(booking, BookingDtoQuery.class);
    }
}
