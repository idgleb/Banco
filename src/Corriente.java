import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Corriente extends Cuenta {

    public Corriente(int cbu, double saldo, Cliente cliente) {
        super(cbu, saldo, cliente, TipoCuenta.CORRIENTE);
    }

    @Override
    public void depositar() {
        double monto = MisFunciones.pedirNumeroMasCero("Ingrese el monto para depositar:");
        if (monto == -1) return;

        setSaldo(getSaldo() + monto);
        TipoTransaccion tipoTransaccion = TipoTransaccion.DEPOSITO;
        String comment = "exito";
        Transaccion transaccion = new Transaccion(monto, tipoTransaccion, comment, this);
        this.getTransacciones().add(transaccion);


    }

    @Override
    public void depositar(double monto, LocalDateTime fecha) {

        setSaldo(getSaldo() + monto);
        TipoTransaccion tipoTransaccion = TipoTransaccion.DEPOSITO;
        String comment = "exito";
        Transaccion transaccion = new Transaccion(fecha, monto, tipoTransaccion, comment, this);
        this.getTransacciones().add(transaccion);

    }

    @Override
    public void retirar() {
        double monto = MisFunciones.pedirDoubleMasCero("Ingrese el monto para retirar:");
        if (monto == -1) return;

        if (monto <= getSaldo()) {

            setSaldo(getSaldo() - monto);
            TipoTransaccion tipoTransaccion = TipoTransaccion.RETIRO;
            String comment = "exito";
            Transaccion transaccion = new Transaccion(monto, tipoTransaccion, comment, this);
            this.getTransacciones().add(transaccion);
            JOptionPane.showMessageDialog(null, "Retireste " + monto + " con exito");

        } else JOptionPane.showMessageDialog(null, "Saldo insuficiente para retirar");
    }

    @Override
    public void retirar(double monto, LocalDateTime fecha) {
        if (monto <= getSaldo()) {

            setSaldo(getSaldo() - monto);
            TipoTransaccion tipoTransaccion = TipoTransaccion.RETIRO;
            String comment = "exito";
            Transaccion transaccion = new Transaccion(fecha, monto, tipoTransaccion, comment, this);
            this.getTransacciones().add(transaccion);

        } else JOptionPane.showMessageDialog(null, "Saldo insuficiente para retirar");
    }

    @Override
    public void transferir() {
        int cbu = MisFunciones.pedirNumeroMasCero("Ingrese el CBU de destino:");
        if (cbu == -1) return;
        if (this.getCliente().getBanco().buscarCBU(cbu)) {
            if (cbu == this.getCbu()) {
                JOptionPane.showMessageDialog(null, "No podes transferir a mismo CBU");
                return;
            }
            double monto = MisFunciones.pedirDoubleMasCero("Ingrese el monto para transferir:");
            if (monto == -1) return;

            if (monto <= this.getSaldo()) {

                Cuenta cuentaDestino = this.getCliente().getBanco().getCuentaPorCBU(cbu);
                this.setSaldo(getSaldo() - monto);
                TipoTransaccion tipoTransaccion = TipoTransaccion.TRANSFERENCIA;
                String comment = "a " + cuentaDestino.getCliente().getNombre() + "(CBU: " + cbu + ")";
                Transaccion transaccion = new Transaccion(monto, tipoTransaccion, comment, this);
                this.getTransacciones().add(transaccion);

                cuentaDestino.setSaldo(cuentaDestino.getSaldo() + monto);
                comment = "de " + this.getCliente().getNombre() + "(CBU: " + this.getCbu() + ")";
                transaccion = new Transaccion(monto, tipoTransaccion, comment, cuentaDestino);
                cuentaDestino.getTransacciones().add(transaccion);

                JOptionPane.showMessageDialog(null, "Transferiste " + monto + " con exito.");

            } else JOptionPane.showMessageDialog(null, "Saldo insuficiente para transferir");
        } else JOptionPane.showMessageDialog(null, "CBU no existe");

    }

    @Override
    public void transferir(int cbu, double monto, LocalDateTime fecha) {
        if (this.getCliente().getBanco().buscarCBU(cbu)) {
            if (cbu == this.getCbu()) {
                JOptionPane.showMessageDialog(null, "No podes transferir a mismo CBU");
                return;
            }

            if (monto <= this.getSaldo()) {

                Cuenta cuentaDestino = this.getCliente().getBanco().getCuentaPorCBU(cbu);
                this.setSaldo(getSaldo() - monto);
                TipoTransaccion tipoTransaccion = TipoTransaccion.TRANSFERENCIA;
                String comment = "a " + cuentaDestino.getCliente().getNombre() + "(CBU: " + cbu + ")";
                Transaccion transaccion = new Transaccion(fecha, monto, tipoTransaccion, comment, this);
                this.getTransacciones().add(transaccion);

                cuentaDestino.setSaldo(cuentaDestino.getSaldo() + monto);
                comment = "de " + this.getCliente().getNombre() + "(CBU: " + this.getCbu() + ")";
                transaccion = new Transaccion(fecha, monto, tipoTransaccion, comment, cuentaDestino);
                cuentaDestino.getTransacciones().add(transaccion);

            } else JOptionPane.showMessageDialog(null, "Saldo insuficiente para transferir");
        } else JOptionPane.showMessageDialog(null, "CBU no existe");
    }


}
