package hotel.alura.api.registro;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistroRepository extends JpaRepository<Registro, Long> {

    List<Registro> findByActivoTrue();


    Registro findByIdRegistroContaining(String s);
}
