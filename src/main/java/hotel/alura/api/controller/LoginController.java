package hotel.alura.api.controller;

import hotel.alura.api.infra.security.TokenService;
import hotel.alura.api.login.DTOLogin;
import hotel.alura.api.login.DTORetornoTokenGenerado;
import hotel.alura.api.login.Login;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @PostMapping
    public ResponseEntity autenticarUsuario (@RequestBody @Valid DTOLogin dtoLogin){
        Authentication authentication = new UsernamePasswordAuthenticationToken(dtoLogin.login(),dtoLogin.password());
        var usuarioAutenticado = authenticationManager.authenticate(authentication);
        var token = tokenService.generarToken((Login) usuarioAutenticado.getPrincipal());//se pasa por parametros un modelo de tipo login para que la clase que genera el jwt pueda asignar los valores al crear el token
        return ResponseEntity.ok().body(new DTORetornoTokenGenerado(token));
    }

}
