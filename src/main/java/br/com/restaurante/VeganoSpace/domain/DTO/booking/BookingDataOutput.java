package br.com.restaurante.VeganoSpace.domain.DTO.booking;

import br.com.restaurante.VeganoSpace.domain.Booking;
import br.com.restaurante.VeganoSpace.domain.Client;

import java.time.OffsetDateTime;

public record BookingDataOutput(String table, OffsetDateTime dateTimeBooking, String numberPerson, String code) {
    public BookingDataOutput(Booking booking){
        this(booking.getTable(), booking.getDateTimeBooking(), booking.getNumberPerson(), booking.getCode());
    }
}
