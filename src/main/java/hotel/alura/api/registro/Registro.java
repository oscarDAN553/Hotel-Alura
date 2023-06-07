package hotel.alura.api.registro;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Table(name = "registros")
@Entity(name = "Registro")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Registro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String idRegistro;
    private String fechaEntrada;
    private String fechaSalida;
    private String valor;
    @Enumerated(EnumType.STRING)
    private FormaPagoEnum formaPago;
    private String activo;

    public Registro(DTORegistro dtoRegistro) {
        this.idRegistro = UUID.randomUUID().toString();
        this.fechaEntrada = dtoRegistro.fecha_entrada();
        this.fechaSalida = dtoRegistro.fecha_salida();
        this.valor = dtoRegistro.valor();
        this.formaPago = dtoRegistro.forma_pago();
        this.activo = "1";
    }

    public void actualizarDatos(DTOActualizarRegistro dtoActualizarRegistro) {
        if(dtoActualizarRegistro.fecha_entrada() != null){
            this.fechaEntrada = dtoActualizarRegistro.fecha_entrada();
        }
        if(dtoActualizarRegistro.fecha_salida() != null){
            this.fechaSalida = dtoActualizarRegistro.fecha_salida();
        }
        if(dtoActualizarRegistro.valor() != null){
            this.valor = dtoActualizarRegistro.valor();
        }
        if(dtoActualizarRegistro.forma_pago() != null){
            this.formaPago = dtoActualizarRegistro.forma_pago();
        }
        if(dtoActualizarRegistro.activo() != null){
            this.activo = dtoActualizarRegistro.activo();
        }
    }

    public void desactivarRegistro() {
        this.activo = "0";
    }
}
