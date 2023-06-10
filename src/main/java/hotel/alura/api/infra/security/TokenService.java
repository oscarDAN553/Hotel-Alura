package hotel.alura.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import hotel.alura.api.login.Login;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    public String generarToken(Login login){
        try {
            Algorithm algorithm = Algorithm.HMAC256("1234567");

             return JWT.create()
                    .withIssuer("hotel_alura")//#ed1 emisor del jwt
                    .withSubject(login.getLogin()) // #ed1 dirigido a
                     .withClaim("id", login.getId()) // #ed2 retorna el id
                     .withExpiresAt(fechaDeExpiracion()) // #ed2 establece la fecha de expiracion
                    .sign(algorithm);

        } catch (JWTCreationException exception){
            throw new RuntimeException();
        }
    }
    private Instant fechaDeExpiracion (){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }

    public String identificarUsuarioToken(String tokenFromHeader){
        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256("1234567");
            verifier = JWT.require(algorithm)
                    .withIssuer("hotel_alura")
                    .build()
                    .verify(tokenFromHeader);
            //verifier.getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("error al tratar de decodificar el token");
        }
        if (verifier.getSubject() == null){
            throw new RuntimeException("verifier nulo");
        }
        return verifier.getSubject();
    }
}
