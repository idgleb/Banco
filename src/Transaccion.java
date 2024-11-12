import java.time.LocalDateTime;

public class Transaccion {
    private LocalDateTime fecha;
    private double monto;
    private TipoTransaccion tipo;
    private String comment;
    private Cuenta cuenta;

    public Transaccion(double monto, TipoTransaccion tipo, String comment, Cuenta cuenta) {
        this.fecha = LocalDateTime.now();
        this.monto = monto;
        this.tipo = tipo;
        this.comment = comment;
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

    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "             Transaccion{" +
                "fecha=" + fecha.getDayOfMonth() + "/" + fecha.getMonthValue() + "/" + fecha.getYear() + " " + fecha.getHour() + ":" + fecha.getMinute() + ":" + fecha.getSecond() +
                ", monto=" + monto +
                ", " + tipo + " " + comment  +
                "}\n";
    }
}
