package br.com.restaurante.VeganoSpace.adapters.repository;

import br.com.restaurante.VeganoSpace.core.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface ClientRepository extends JpaRepository<Client, Long> {
    UserDetails findByUserEmail(String email);
}
