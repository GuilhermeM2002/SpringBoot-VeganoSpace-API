package br.com.restaurante.VeganoSpace.services;

import br.com.restaurante.VeganoSpace.application.dto.login.LoginDto;
import br.com.restaurante.VeganoSpace.core.domain.Client;
import br.com.restaurante.VeganoSpace.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    public String userLogin(LoginDto dto){
        var authenticationToken = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        var authentication = authenticationManager.authenticate(authenticationToken);
        var token = tokenService.tokenGenerator((Client) authentication.getPrincipal());

        return token;
    }
}
