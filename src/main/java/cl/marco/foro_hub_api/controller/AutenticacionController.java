package cl.marco.foro_hub_api.controller;

import cl.marco.foro_hub_api.model.dto.AutenticacionUsuarioDTO;
import cl.marco.foro_hub_api.model.dto.JWTTokenDTO;
import cl.marco.foro_hub_api.model.entities.Usuario;
import cl.marco.foro_hub_api.service.TokenService;
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
public class AutenticacionController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid AutenticacionUsuarioDTO datos) {
        System.out.println(datos.toString());
        var authToken = new UsernamePasswordAuthenticationToken(datos.email(), datos.contrasenia());
        System.out.println(authToken.getAuthorities());
        var auth =  authenticationManager.authenticate(authToken);
        var jwtToken = tokenService.generarToken((Usuario) auth.getPrincipal());
        return ResponseEntity.ok(new JWTTokenDTO(jwtToken));
    }
}
