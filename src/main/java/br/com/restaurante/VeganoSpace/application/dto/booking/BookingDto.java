package br.com.restaurante.VeganoSpace.application.dto.booking;

import br.com.restaurante.VeganoSpace.core.domain.Client;

import java.time.OffsetDateTime;

public record BookingDto(Long id, String table, OffsetDateTime dateTimeBooking, String numberPerson, Client client) {
}
