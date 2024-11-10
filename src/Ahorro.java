import javax.swing.*;

public class Ahorro extends Cuenta {
    private int interes;
    private  double minDeposito;

    public Ahorro(int cbu, double saldo, Cliente cliente, int interes, double minDeposito) {
        super(cbu, saldo, cliente);
        this.interes = interes;
        this.minDeposito = minDeposito;
    }


    @Override
    public void depositar() {
        double monto = MisFunciones.pedirNumeroMasCero("Ingrese el monto para depositar:");
        if (monto == -1) return;
        if (monto >= this.minDeposito) {
            setSaldo(getSaldo() + monto);
        }
    }

    @Override
    public void retirar() {
        double monto = MisFunciones.pedirDoubleMasCero("Ingrese el monto para retirar:");
        if (monto == -1) return;
        if (monto <= getSaldo()) {
            setSaldo(getSaldo() - monto);
        }else JOptionPane.showMessageDialog(null, "Saldo insuficiente para retirar");
    }

    @Override
    public void transferir() {
        int cbu = MisFunciones.pedirNumeroMasCero("Ingrese el CBU de destino:");
        //chear si hay este CBU en banco

        double monto = MisFunciones.pedirDoubleMasCero("Ingrese el monto para transferir:");
    }

    @Override
    public String toString() {
        return "Ahorro{" +
                "interes=" + interes +
                ", minDeposito=" + minDeposito +
                "} " + super.toString() + "\n";
    }
}
