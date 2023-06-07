package hotel.alura.api.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DTOUsuario(
        @NotBlank
        String nombre,
        @NotBlank
        String apellido,
        @NotBlank
        @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
        String fecha_nacimiento,
        @NotBlank
        String nacionalidad,
        @NotBlank
        @Pattern(regexp = "\\d{10,12}")
        String telefono,
        @NotBlank
        String id_registro
) {
}
