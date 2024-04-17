package br.com.restaurante.VeganoSpace.services;

import br.com.restaurante.VeganoSpace.adapters.repository.ClientRepository;
import br.com.restaurante.VeganoSpace.application.dto.client.ClientDto;
import br.com.restaurante.VeganoSpace.application.dto.client.ClientDtoQuery;
import br.com.restaurante.VeganoSpace.application.dto.client.ClientDtoUpdate;
import br.com.restaurante.VeganoSpace.core.domain.Client;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ClientRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ClientDto persistClient(ClientDto dto){
        var client = new Client(dto);
        client.getUser().setPassword(passwordEncoder.encode(client.getPassword()));
        repository.save(client);

        return mapper.map(client, ClientDto.class);
    }

    public ClientDtoUpdate updateClient(ClientDtoUpdate dto){
        var client = repository.getReferenceById(dto.id());
        client.UpdateClient(dto);
        repository.save(client);

        return mapper.map(client, ClientDtoUpdate.class);
    }

    public Page<ClientDtoQuery> findAllClient(Pageable page){
        var listClient = repository.findAll(page).map(ClientDtoQuery::new);

        return  listClient;
    }

    public void deleteClient(Long id){
        var clientDelete = repository.getReferenceById(id);
        repository.delete(clientDelete);
    }

    public ClientDtoQuery findOneClient(Long id){
        var client = repository.getReferenceById(id);

        return mapper.map(client, ClientDtoQuery.class);
    }
}
