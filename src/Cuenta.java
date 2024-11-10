import java.util.ArrayList;
import java.util.List;

public abstract class Cuenta {
    private int cbu;
    private double saldo;
    private Cliente cliente;
    private List<Transaccion> transacciones = new ArrayList<>();

    public Cuenta(int cbu, double saldo, Cliente cliente) {
        this.cbu = cbu;
        this.saldo = saldo;
        this.cliente = cliente;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String historiaTransacciones() {
        String historia = "";
        for (Transaccion transaccion : transacciones) {
            historia = historia + transaccion.toString() + "\n";
        }
        return historia;
    }

    public abstract void depositar();

    public abstract void retirar();

    public abstract void transferir();

    public int getCbu() {
        return cbu;
    }

    public void setCbu(int cbu) {
        this.cbu = cbu;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(List<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }


    @Override
    public String toString() {
        return "Cuenta{" +
                "cbu=" + cbu +
                ", saldo=" + saldo +
                ", cliente=" + cliente +
                ", transacciones=" + transacciones +
                "}\n";
    }



}
