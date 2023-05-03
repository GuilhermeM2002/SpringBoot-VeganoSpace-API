package br.com.restaurante.VeganoSpace.domain;

import br.com.restaurante.VeganoSpace.domain.DTO.user.UserData;
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

    public User(UserData data){
        this.email = data.email();
        this.password = data.password();
    }

    public void UserUpdate(UserData data){
        if (data.email() != null){
            this.email = data.email();
        }
        if (data.password() != null){
            this.password = data.password();
        }
    }
}
