package br.com.restaurante.VeganoSpace.application.dto.client;

import br.com.restaurante.VeganoSpace.core.domain.Client;

import java.time.LocalDate;

public record ClientDtoOutput(String cpf, String name, LocalDate dateBirth) {
    public ClientDtoOutput(Client client){
        this(client.getCpf(), client.getName(), client.getDateBirth());
    }
}
