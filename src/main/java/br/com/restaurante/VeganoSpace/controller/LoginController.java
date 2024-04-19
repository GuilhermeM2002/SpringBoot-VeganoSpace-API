package br.com.restaurante.VeganoSpace.controller;

import br.com.restaurante.VeganoSpace.application.dto.login.LoginDto;
import br.com.restaurante.VeganoSpace.infra.security.ClientDataToken;
import br.com.restaurante.VeganoSpace.services.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;
    @PostMapping
    public ResponseEntity login(@RequestBody @Valid LoginDto dto){
        var token = loginService.userLogin(dto);

        return ResponseEntity.ok(new ClientDataToken(token));
    }
}
