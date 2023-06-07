package hotel.alura.api.registro;

import lombok.Getter;

@Getter

public class RetornoRegistro {
    //private Long id;
    private String id_registro;
    private String fecha_entrada;
    private String fecha_salida;
    private String valor;
    private FormaPagoEnum forma_pago;
    //private String activo;

    public RetornoRegistro(Registro registro) {
        //this.id = registro.getId();
        this.id_registro = registro.getIdRegistro();
        this.fecha_entrada = registro.getFechaEntrada();
        this.fecha_salida = registro.getFechaSalida();
        this.valor = registro.getValor();
        this.forma_pago = registro.getFormaPago();
        //this.activo = registro.getActivo();
    }
}
