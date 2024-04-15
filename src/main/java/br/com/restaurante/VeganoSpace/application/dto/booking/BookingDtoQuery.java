package br.com.restaurante.VeganoSpace.application.dto.booking;

import br.com.restaurante.VeganoSpace.core.domain.Booking;

import java.time.OffsetDateTime;

public record BookingDtoQuery(String table, OffsetDateTime dateTimeBooking, String numberPerson, String code) {
    public BookingDtoQuery(Booking booking){
        this(booking.getTable(), booking.getDateTimeBooking(), booking.getNumberPerson(), booking.getCode());
    }
}
