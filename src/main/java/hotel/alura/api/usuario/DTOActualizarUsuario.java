package hotel.alura.api.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DTOActualizarUsuario(
        @NotNull
        Long id,
        String nombre,

        String apellido,

        @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
        String fecha_nacimiento,

        String nacionalidad,

        @Pattern(regexp = "\\d{10,12}")
        String telefono

) {
}
