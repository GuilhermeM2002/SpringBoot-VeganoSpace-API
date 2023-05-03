package br.com.restaurante.VeganoSpace.domain.DTO.booking;

import br.com.restaurante.VeganoSpace.domain.Client;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingDataUpdate {
    @NotBlank
    private String table;
    @NotNull
    private OffsetDateTime dateTimeBooking;
    @NotBlank
    private String numberPerson;
    @NotNull
    private Client client;
}
