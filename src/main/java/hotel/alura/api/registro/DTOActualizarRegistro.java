package hotel.alura.api.registro;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DTOActualizarRegistro(
        @NotNull
        String id_registro,
        @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
        String fecha_entrada,
        @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
        String fecha_salida,
        @Pattern(regexp ="^[-+]?\\d*\\.\\d+$")
        String valor,
        FormaPagoEnum forma_pago,
        @Pattern(regexp = "^[01]$" )
        String activo
) {
}
