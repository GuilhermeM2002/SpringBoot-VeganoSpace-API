package br.com.restaurante.VeganoSpace.application.dto.booking;

import br.com.restaurante.VeganoSpace.core.domain.Booking;

import java.time.OffsetDateTime;

public record BookingDtoOutput(String table, OffsetDateTime dateTimeBooking, String numberPerson, String code) {
    public BookingDtoOutput(Booking booking){
        this(booking.getTable(), booking.getDateTimeBooking(), booking.getNumberPerson(), booking.getCode());
    }
}
