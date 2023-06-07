package hotel.alura.api.registro;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.Date;

public record DTORegistro(
        @NotBlank
        @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
        String fecha_entrada,
        @NotBlank
        @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
        String fecha_salida,
        @NotBlank
        @Pattern(regexp ="^[-+]?\\d*\\.\\d+$")
        String valor,
        @NotNull
        FormaPagoEnum forma_pago

) {
}
