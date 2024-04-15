package br.com.restaurante.VeganoSpace.application.dto.mapper;

import br.com.restaurante.VeganoSpace.application.dto.booking.BookingDtoUpdate;
import br.com.restaurante.VeganoSpace.core.domain.Booking;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {
    @Autowired
    private ModelMapper modelMapper;
    public BookingDtoUpdate toModel(Booking booking){
        return modelMapper.map(booking, BookingDtoUpdate.class);
    }

    public Booking toEntity(BookingDtoUpdate bookingDtoUpdate){
        return modelMapper.map(bookingDtoUpdate, Booking.class);
    }

}
