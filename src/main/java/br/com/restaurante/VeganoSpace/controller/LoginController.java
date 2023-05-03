package br.com.restaurante.VeganoSpace.controller;

import br.com.restaurante.VeganoSpace.domain.Client;
import br.com.restaurante.VeganoSpace.domain.DTO.login.DataLogin;
import br.com.restaurante.VeganoSpace.domain.User;
import br.com.restaurante.VeganoSpace.infra.security.ClientDataToken;
import br.com.restaurante.VeganoSpace.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping
    public ResponseEntity login(@RequestBody @Valid DataLogin data){
        var authenticationToken = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var authentication = authenticationManager.authenticate(authenticationToken);
        var token = tokenService.tokenGenerator((Client) authentication.getPrincipal());


        return ResponseEntity.ok(new ClientDataToken(token));

    }

}
