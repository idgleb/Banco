import javax.swing.*;
import java.time.LocalDateTime;

public class Ahorro extends Cuenta {
    private int interes;
    private double minDeposito;

    public Ahorro(int cbu, double saldo, Cliente cliente, int interes, double minDeposito) {

        super(cbu, saldo, cliente, TipoCuenta.AHORRO);
        this.interes = interes;
        this.minDeposito = minDeposito;

    }


    @Override
    public void depositar() {
        double monto = MisFunciones.pedirNumeroMasCero("Ingrese el monto para depositar:");
        if (monto == -1) return;

        if (monto >= this.minDeposito) {
            setSaldo(getSaldo() + monto);

            TipoTransaccion tipoTransaccion = TipoTransaccion.DEPOSITO;
            String comment = "exito";
            Transaccion transaccion = new Transaccion(monto, tipoTransaccion, comment, this);
            this.getTransacciones().add(transaccion);

            JOptionPane.showMessageDialog(null, "Depositaste " + monto + " con exito");

        } else JOptionPane.showMessageDialog(null, "Min Deposito es " + minDeposito);
    }

    @Override
    public void depositar(double monto, LocalDateTime fecha) {
        if (monto >= this.minDeposito) {
            setSaldo(getSaldo() + monto);

            TipoTransaccion tipoTransaccion = TipoTransaccion.DEPOSITO;
            String comment = "exito";
            Transaccion transaccion = new Transaccion(fecha, monto, tipoTransaccion, comment, this);
            this.getTransacciones().add(transaccion);

        } else JOptionPane.showMessageDialog(null, "Min Deposito es " + minDeposito);
    }

    @Override
    public void retirar() {
        double monto = MisFunciones.pedirDoubleMasCero("Ingrese el monto para retirar:");
        if (monto == -1) return;

        double montoInteres = monto * interes / 100;
        if (monto + montoInteres <= getSaldo()) {

            setSaldo(getSaldo() - monto - montoInteres);

            TipoTransaccion tipoTransaccion = TipoTransaccion.RETIRO;
            String comment = "interes -" + montoInteres;
            Transaccion transaccion = new Transaccion(monto, tipoTransaccion, comment, this);
            this.getTransacciones().add(transaccion);

            JOptionPane.showMessageDialog(null, "Retiraste " + monto + " con exito. Tambien pagaste interes: " + montoInteres);

        } else JOptionPane.showMessageDialog(null, "Saldo insuficiente para retirar");
    }

    @Override
    public void retirar(double monto, LocalDateTime fecha) {
        double montoInteres = monto * interes / 100;
        if (monto + montoInteres <= getSaldo()) {

            setSaldo(getSaldo() - monto - montoInteres);

            TipoTransaccion tipoTransaccion = TipoTransaccion.RETIRO;
            String comment = "interes -" + montoInteres;
            Transaccion transaccion = new Transaccion(fecha, monto, tipoTransaccion, comment, this);
            this.getTransacciones().add(transaccion);

        } else JOptionPane.showMessageDialog(null, "Saldo insuficiente para retirar");
    }

    @Override
    public void transferir() {
        int cbu = MisFunciones.pedirNumeroMasCero("Ingrese el CBU de destino:");
        if (cbu == -1) return;
        //chequear si hay este CBU en banco
        if (this.getCliente().getBanco().buscarCBU(cbu)) {
            if (cbu == this.getCbu()) {
                JOptionPane.showMessageDialog(null, "No podes transferir a mismo CBU");
                return;
            }
            double monto = MisFunciones.pedirDoubleMasCero("Ingrese el monto para transferir:");
            if (monto == -1) return;

            double montoInteres = monto * interes / 100;
            if (monto + montoInteres <= this.getSaldo()) {

                Cuenta cuentaDestino = this.getCliente().getBanco().getCuentaPorCBU(cbu);
                this.setSaldo(getSaldo() - monto - montoInteres);
                TipoTransaccion tipoTransaccion = TipoTransaccion.TRANSFERENCIA;
                String comment = "a " + cuentaDestino.getCliente().getNombre() + "(CBU: " + cbu + ") interes -" + montoInteres;
                Transaccion transaccion = new Transaccion(monto, tipoTransaccion, comment, this);
                this.getTransacciones().add(transaccion);

                cuentaDestino.setSaldo(cuentaDestino.getSaldo() + monto);
                comment = "de " + this.getCliente().getNombre() + "(CBU: " + this.getCbu() + ")";
                transaccion = new Transaccion(monto, tipoTransaccion, comment, cuentaDestino);
                cuentaDestino.getTransacciones().add(transaccion);

                JOptionPane.showMessageDialog(null, "Transferiste " + monto + " con exito. Tambien pagaste interes: " + montoInteres);

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

            double montoInteres = monto * interes / 100;
            if (monto + montoInteres <= this.getSaldo()) {

                Cuenta cuentaDestino = this.getCliente().getBanco().getCuentaPorCBU(cbu);
                this.setSaldo(getSaldo() - monto - montoInteres);
                TipoTransaccion tipoTransaccion = TipoTransaccion.TRANSFERENCIA;
                String comment = "a " + cuentaDestino.getCliente().getNombre() + "(CBU: " + cbu + ") interes -" + montoInteres;
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
