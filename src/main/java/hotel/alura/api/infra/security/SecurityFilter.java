package hotel.alura.api.infra.security;

import hotel.alura.api.login.LoginRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private LoginRepository loginRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //////////////// en esta seccion se obtiene el token del header y se le da formato
        System.out.println("se ejecuto el filtro");
        var tokenFromHeader = request.getHeader("Authorization");

        if (tokenFromHeader != null){
            tokenFromHeader = tokenFromHeader.replace("Bearer ", "");
            var subjet = tokenService.identificarUsuarioToken(tokenFromHeader);
            if (subjet != null){
                //forzamos un inicio de sesion en el sistema
                System.out.println(subjet);
                var login = loginRepository.findByLogin(subjet);
                var authentication = new UsernamePasswordAuthenticationToken(login, null, login.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        /////////////////

        filterChain.doFilter(request,response);
    }
}
