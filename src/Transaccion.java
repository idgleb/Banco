import java.time.LocalDateTime;

public class Transaccion {
    private double monto;
    private LocalDateTime fecha;
    private TipoTransaccion tipo;
    private Cuenta cuenta;

    public Transaccion(double monto, LocalDateTime fecha, TipoTransaccion tipo, Cuenta cuenta) {
        this.monto = monto;
        this.fecha = LocalDateTime.now();
        this.tipo = tipo;
        this.cuenta = cuenta;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public TipoTransaccion getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransaccion tipo) {
        this.tipo = tipo;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public String toString() {
        return "Transaccion{" +
                "monto=" + monto +
                ", fecha=" + fecha +
                ", tipo=" + tipo +
                ", cuenta=" + cuenta +
                "}\n";
    }
}
