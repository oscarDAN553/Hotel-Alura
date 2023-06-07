package hotel.alura.api.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String fechaNacimiento;
    private String nacionalidad;
    private String telefono;
    private String idRegistro;
    private String activo;

    public Usuario(DTOUsuario dtoUsuario) {
        this.nombre = dtoUsuario.nombre();
        this.apellido = dtoUsuario.apellido();
        this.fechaNacimiento = dtoUsuario.fecha_nacimiento();
        this.nacionalidad = dtoUsuario.nacionalidad();
        this.telefono = dtoUsuario.telefono();
        this.idRegistro = dtoUsuario.id_registro();
        this.activo = "1";
    }

    public void actualizarDatos(DTOActualizarUsuario dtoActualizarUsuario) {
        if(dtoActualizarUsuario.nombre() != null){
         this.nombre = dtoActualizarUsuario.nombre();
        }
        if(dtoActualizarUsuario.apellido() != null){
            this.apellido = dtoActualizarUsuario.apellido();
        }
        if(dtoActualizarUsuario.fecha_nacimiento() != null){
            this.fechaNacimiento = dtoActualizarUsuario.fecha_nacimiento();
        }
        if(dtoActualizarUsuario.nacionalidad() != null){
            this.nacionalidad  = dtoActualizarUsuario.nacionalidad();
        }
        if(dtoActualizarUsuario.telefono() != null){
            this.telefono = dtoActualizarUsuario.telefono();
        }
    }

    public void desactivarUsuario() {
        this.activo = "0";
    }
}
