package br.com.restaurante.VeganoSpace.application.dto.client;

import br.com.restaurante.VeganoSpace.core.domain.Client;

import java.time.LocalDate;

public record ClientDtoQuery(Long id, String cpf, String name, LocalDate dateBirth) {
    public ClientDtoQuery(Client client){
        this(client.getId(), client.getCpf(), client.getName(), client.getDateBirth());
    }

}
