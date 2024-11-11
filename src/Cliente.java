import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nombre;
    private int dni;
    private Banco banco;
    private List<Cuenta> cuentas  = new ArrayList<>();

    public Cliente(String nombre, int dni, Banco banco) {
        this.nombre = nombre;
        this.dni = dni;
        this.banco = banco;
    }

    public static void operarCuentas(Banco banco){

        if (banco.getClientes().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay clientes");
            return;
        }

        int idCliente = JOptionPane.showOptionDialog(null, "Quien sos?", null,
                0, 0, null, banco.getClientes().toArray(), 0);

        if (idCliente == -1) return;
        Cliente cliente = banco.getClientes().get(idCliente);

        if (cliente.getCuentas().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No tenes cuentas");
            return;
        }

        int idCuenta = 0;
        do{
            idCuenta = JOptionPane.showOptionDialog(null, "Elije la cuenta", null,
                    0, 0, null, cliente.getCuentas().toArray(), 0);

            if (idCuenta == -1) return;

            Cuenta cuenta = cliente.getCuentas().get(idCuenta);

            int idOperacion = 0;
            do {
                idOperacion = JOptionPane.showOptionDialog(null, "Elije opericion", null,
                        0, 0, null, OperacioneCliente.values(), 0);
                switch (idOperacion) {
                    case 0:
                        JOptionPane.showMessageDialog(null,"Tu saldo es " + cuenta.getSaldo());
                        break;
                    case 1:
                        JOptionPane.showMessageDialog(null,"Historia de cuenta "+ cuenta+ " \n" + cuenta.historiaTransacciones());
                        break;
                    case 2:
                        cuenta.depositar();
                        break;
                    case 3:
                        cuenta.retirar();
                        break;
                    case 4:
                        cuenta.transferir();
                        break;
                }
            } while (idOperacion != -1);

        }while (true);








    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nombre='" + nombre + '\'' +
                ", dni=" + dni +
                "}\n";
    }
}
