package br.com.restaurante.VeganoSpace.controller;

import br.com.restaurante.VeganoSpace.application.dto.client.ClientDto;
import br.com.restaurante.VeganoSpace.application.dto.client.ClientDtoQuery;
import br.com.restaurante.VeganoSpace.application.dto.client.ClientDtoOutput;
import br.com.restaurante.VeganoSpace.application.dto.client.ClientDtoUpdate;
import br.com.restaurante.VeganoSpace.core.domain.Client;
import br.com.restaurante.VeganoSpace.services.ClientService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientService service;
    @Autowired
    private ModelMapper mapper;

    @PostMapping("/sign-up")
    @Transactional
    public ResponseEntity persist(
            @RequestBody @Valid ClientDto dto, UriComponentsBuilder uriBuilder){
        var client = service.persistClient(dto);
        var uri = uriBuilder.path("/client/{id}").buildAndExpand(client.id()).toUri();

        return ResponseEntity.created(uri).body(
                new ClientDtoOutput(mapper.map(client, Client.class)));
    }

    @PutMapping
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity update(@RequestBody @Valid ClientDtoUpdate dto){
        var client = service.updateClient(dto);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Page<ClientDtoQuery>> findAll(
            @PageableDefault(size = 10, sort = {"name"}) Pageable page){
        var listClient = service.findAllClient(page);

        return  ResponseEntity.ok(listClient);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity delete(@PathVariable Long id){
        service.deleteClient(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<ClientDtoQuery> findOne(@PathVariable Long id){
        var client = service.findOneClient(id);

        return ResponseEntity.ok(new ClientDtoQuery(mapper.map(client, Client.class)));
    }
}
