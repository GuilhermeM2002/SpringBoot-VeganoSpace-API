package br.com.restaurante.VeganoSpace.domain;

import br.com.restaurante.VeganoSpace.domain.DTO.client.ClientData;
import br.com.restaurante.VeganoSpace.domain.DTO.client.ClientDataUpdate;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "T_CLIENT")
public class Client implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cpf;
    private String name;
    @Embedded
    private User user;
    @Column(name = "dt_birth")
    private LocalDate dateBirth;


    public Client(ClientData data){
        this.cpf = data.cpf();
        this.name = data.name();
        this.dateBirth = data.dateBirth();
        this.user = new User(data.user());
    }

    public void UpdateClient(ClientDataUpdate data){
        if (data.name() != null){
            this.name = data.name();
        }
        if (data.dateBirth() != null){
            this.dateBirth = data.dateBirth();
        }
        if (data.user() != null){
            this.user.UserUpdate(data.user());
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_CLIENT"));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
