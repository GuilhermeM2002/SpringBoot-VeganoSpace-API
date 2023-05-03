package br.com.restaurante.VeganoSpace.controller;

import br.com.restaurante.VeganoSpace.domain.DTO.client.ClientData;
import br.com.restaurante.VeganoSpace.domain.DTO.client.ClientDataQuery;
import br.com.restaurante.VeganoSpace.domain.DTO.client.ClientDataOutput;
import br.com.restaurante.VeganoSpace.domain.DTO.client.ClientDataUpdate;
import br.com.restaurante.VeganoSpace.domain.Client;
import br.com.restaurante.VeganoSpace.domain.repository.ClientRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/sign-up")
    @Transactional
    public ResponseEntity persist(@RequestBody @Valid ClientData data, UriComponentsBuilder uriBuilder){
        var client = new Client(data);
        client.getUser().setPassword(passwordEncoder.encode(client.getPassword()));
        repository.save(client);

        var uri = uriBuilder.path("/client/{id}").buildAndExpand(client.getId()).toUri();

        return ResponseEntity.created(uri).body(new ClientDataOutput(client));
    }

    @PutMapping
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity update(@RequestBody @Valid ClientDataUpdate data){
        var client = repository.getReferenceById(data.id());
        client.UpdateClient(data);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Page<ClientDataQuery>> findAll(@PageableDefault(size = 10, sort = {"name"}) Pageable page){
        var listClient = repository.findAll(page).map(ClientDataQuery::new);

        return  ResponseEntity.ok(listClient);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity delete(@PathVariable Long id){
        var clientDelete = repository.getReferenceById(id);
        repository.delete(clientDelete);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<ClientDataQuery> findOne(@PathVariable Long id){
        var client = repository.getReferenceById(id);

        return ResponseEntity.ok(new ClientDataQuery(client));
    }
}
