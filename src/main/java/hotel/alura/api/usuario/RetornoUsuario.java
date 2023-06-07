package hotel.alura.api.usuario;

import lombok.Getter;

@Getter
public class RetornoUsuario {
    private Long id;
    private String nombre;
    private String apellido;
    private String fecha_nacimiento;
    private String nacionalidad;
    private String telefono;
    private String id_registro;

    public RetornoUsuario(Usuario usuario) {
        this.id = usuario.getId();
        this.nombre = usuario.getNombre();
        this.apellido = usuario.getApellido();
        this.fecha_nacimiento = usuario.getFechaNacimiento();
        this.nacionalidad = usuario.getNacionalidad();
        this.telefono = usuario.getTelefono();
        this.id_registro = usuario.getIdRegistro();
    }
}
