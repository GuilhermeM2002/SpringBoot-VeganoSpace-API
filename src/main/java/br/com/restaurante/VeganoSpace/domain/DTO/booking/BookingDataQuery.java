package br.com.restaurante.VeganoSpace.domain.DTO.booking;

import br.com.restaurante.VeganoSpace.domain.Booking;

import java.time.OffsetDateTime;

public record BookingDataQuery(String table, OffsetDateTime dateTimeBooking, String numberPerson, String code) {
    public BookingDataQuery(Booking booking){
        this(booking.getTable(), booking.getDateTimeBooking(), booking.getNumberPerson(), booking.getCode());
    }
}
