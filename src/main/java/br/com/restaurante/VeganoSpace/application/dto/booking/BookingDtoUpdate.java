package br.com.restaurante.VeganoSpace.application.dto.booking;

import br.com.restaurante.VeganoSpace.core.domain.Client;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingDtoUpdate {
    @NotBlank
    private String table;
    @NotNull
    private OffsetDateTime dateTimeBooking;
    @NotBlank
    private String numberPerson;
    @NotNull
    private Client client;
}
