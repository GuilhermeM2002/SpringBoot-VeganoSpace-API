package br.com.restaurante.VeganoSpace.domain.DTO.client;

import br.com.restaurante.VeganoSpace.domain.Client;

import java.time.LocalDate;

public record ClientDataQuery(Long id, String cpf, String name, LocalDate dateBirth) {
    public ClientDataQuery(Client client){
        this(client.getId(), client.getCpf(), client.getName(), client.getDateBirth());
    }

}
