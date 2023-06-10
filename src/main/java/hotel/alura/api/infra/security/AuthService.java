package hotel.alura.api.infra.security;

import hotel.alura.api.login.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class AuthService implements UserDetailsService {//Con UserDetailsService implementamos una interfaz de spring que usa para el proceso de autenticacion del usuario
    @Autowired
    private LoginRepository loginRepository;

    @Override  //metodo de la clase UserDetailsService
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return loginRepository.findByLogin(username);
    }
}
