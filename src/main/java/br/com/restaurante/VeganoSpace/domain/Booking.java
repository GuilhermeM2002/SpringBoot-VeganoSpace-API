package br.com.restaurante.VeganoSpace.domain;

import br.com.restaurante.VeganoSpace.domain.DTO.booking.BookingData;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
@Getter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "T_BOOKING")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "bk_table")
    private String table;
    @Column(name = "dt_booking")
    private OffsetDateTime dateTimeBooking;
    @Column(name = "nr_person")
    private String numberPerson;
    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;
    public Booking(BookingData data) {
        this.table = data.table();
        this.dateTimeBooking = data.dateTimeBooking();
        this.numberPerson = data.numberPerson();
        this.client = data.client();
    }

    public void BookingUpdate(Booking data){
        if (data.getTable() != null){
            this.table = data.getTable();
        }
        if (data.getDateTimeBooking() != null){
            this.dateTimeBooking = data.getDateTimeBooking();
        }
        if (data.getNumberPerson() != null){
            this.numberPerson = data.getNumberPerson();
        }
        if (data.getClient() != null){
            this.client = data.getClient();
        }
    }
}
