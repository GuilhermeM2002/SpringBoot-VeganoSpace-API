package br.com.restaurante.VeganoSpace.core.domain;

import br.com.restaurante.VeganoSpace.application.dto.user.UserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User{
    private String email;
    private String password;

    public User(UserDto data){
        this.email = data.email();
        this.password = data.password();
    }

    public void UserUpdate(UserDto data){
        if (data.email() != null){
            this.email = data.email();
        }
        if (data.password() != null){
            this.password = data.password();
        }
    }
}
