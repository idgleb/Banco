import javax.swing.*;

public class Corriente extends Cuenta {
    public Corriente(int cbu, double saldo, Cliente cliente) {
        super(cbu, saldo, cliente);
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
        JOptionPane.showMessageDialog(null, "Depositaste " + monto + " con exito");

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
    public void transferir() {
        int cbu = MisFunciones.pedirNumeroMasCero("Ingrese el CBU de destino:");

        if (this.getCliente().getBanco().buscarCBU(cbu)) {
            double monto = MisFunciones.pedirDoubleMasCero("Ingrese el monto para transferir:");
            if (monto == -1) return;

            if (monto <= this.getSaldo()) {

                Cuenta cuentaDestino = this.getCliente().getBanco().getCuentaPorCBU(cbu);
                this.setSaldo(getSaldo() - monto);
                TipoTransaccion tipoTransaccion = TipoTransaccion.TRANSFERENCIA;
                String comment = "a " + cuentaDestino.getCliente().getNombre();
                Transaccion transaccion = new Transaccion(monto, tipoTransaccion, comment, this);
                this.getTransacciones().add(transaccion);

                cuentaDestino.setSaldo(cuentaDestino.getSaldo() + monto);
                comment = "de " + this.getCliente().getNombre();
                transaccion = new Transaccion(monto, tipoTransaccion, comment, cuentaDestino);
                cuentaDestino.getTransacciones().add(transaccion);

            } else JOptionPane.showMessageDialog(null, "Saldo insuficiente para transferir");
        } else JOptionPane.showMessageDialog(null, "CBU no existe");

    }


    @Override
    public String toString() {
        return "Corriente{} " + super.toString() + "\n";
    }
}
