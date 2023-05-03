package br.com.restaurante.VeganoSpace.domain.DTO.mapper;

import br.com.restaurante.VeganoSpace.domain.DTO.booking.BookingDataUpdate;
import br.com.restaurante.VeganoSpace.domain.Booking;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {
    @Autowired
    private ModelMapper modelMapper;
    public BookingDataUpdate toModel(Booking booking){
        return modelMapper.map(booking, BookingDataUpdate.class);
    }

    public Booking toEntity(BookingDataUpdate bookingDataUpdate){
        return modelMapper.map(bookingDataUpdate, Booking.class);
    }

}
