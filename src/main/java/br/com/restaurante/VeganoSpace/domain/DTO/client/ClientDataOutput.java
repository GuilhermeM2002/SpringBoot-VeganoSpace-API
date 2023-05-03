package br.com.restaurante.VeganoSpace.domain.DTO.client;

import br.com.restaurante.VeganoSpace.domain.Client;

import java.time.LocalDate;

public record ClientDataOutput(String cpf, String name, LocalDate dateBirth) {
    public ClientDataOutput(Client client){
        this(client.getCpf(), client.getName(), client.getDateBirth());
    }
}
