package br.com.restaurante.VeganoSpace.domain.DTO.booking;

import br.com.restaurante.VeganoSpace.domain.Client;

import java.time.OffsetDateTime;

public record BookingData(String table, OffsetDateTime dateTimeBooking, String numberPerson, Client client) {
}
